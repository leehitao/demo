package com.xrt.bzj.service.es;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xrt.bzj.dao.entity.ElasticEntity;
import org.apache.http.HttpHost;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.reindex.DeleteByQueryRequest;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;

@Component
public class DemoEs {

    @Value("${es.host}")
    public String host;
    @Value("${es.port}")
    public int port;
    @Value("${es.scheme}")
    public String scheme;

    public static final String INDEX_NAME = "book";
    /**
     * 根据自己的业务去定义(这里是测试使用)
     **/
    public static final String CREATE_INDEX = "{\n" +
            "    \"properties\": {\n" +
            "      \"id\":{\n" +
            "        \"type\":\"integer\"\n" +
            "      },\n" +
            "      \"name\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"analyzer\": \"ik_max_word\",\n" +
            "        \"search_analyzer\": \"ik_smart\"\n" +
            "      },\n" +
            "      \"price\":{\n" +
            "        \"type\":\"double\"\n" +
            "      },\n" +
            "      \"description\":{\n" +
            "        \"type\":\"text\",\n" +
            "        \"index\": true,\n" +
            "        \"analyzer\": \"ik_max_word\",\n" +
            "        \"search_analyzer\": \"ik_smart\"\n" +
            "      }\n" +
            "    }\n" +
            "  }";

    public static RestHighLevelClient client = null;

    @PostConstruct
    public void init() {
        try {
            if (client != null) {
                client.close();
            }
            client = new RestHighLevelClient(RestClient.builder(new HttpHost(host, port, scheme)));
            if (this.indexExist(INDEX_NAME)) {
                return;
            }
            CreateIndexRequest request = new CreateIndexRequest(INDEX_NAME);
            request.settings(Settings.builder().put("index.number_of_shards", 1).put("index.number_of_replicas", 0));
            System.out.println(CREATE_INDEX);
            request.mapping(CREATE_INDEX, XContentType.JSON);
            CreateIndexResponse res = client.indices().create(request, RequestOptions.DEFAULT);
            if (!res.isAcknowledged()) {
                throw new RuntimeException("初始化失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    /**
     * Description: 判断某个index是否存在
     *
     * @param index index名
     * @return boolean
     */
    public boolean indexExist(String index) throws Exception {
        GetIndexRequest request = new GetIndexRequest(index);
        request.local(false);
        request.humanReadable(true);
        request.includeDefaults(false);
        return client.indices().exists(request, RequestOptions.DEFAULT);
    }

    /**
     * Description: 插入/更新一条记录
     *
     * @param index  index
     * @param entity 对象
     */
    public void insertOrUpdateOne(String index, ElasticEntity entity) {
        IndexRequest request = new IndexRequest(index);
        request.id(entity.getId());
        request.source(JSON.toJSONString(entity.getData()), XContentType.JSON);
        try {
            client.index(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: 批量插入数据
     *
     * @param index index
     * @param list  带插入列表
     */
    public void insertBatch(String index, List<ElasticEntity> list) {
        BulkRequest request = new BulkRequest();
        list.forEach(item -> request.add(new IndexRequest(index).id(item.getId())
                .source(JSON.toJSONString(item.getData()), XContentType.JSON)));
        try {
            client.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: 批量删除
     *
     * @param index  index
     * @param idList 待删除列表
     */
    public <T> void deleteBatch(String index, Collection<T> idList) {
        BulkRequest request = new BulkRequest();
        idList.forEach(item -> request.add(new DeleteRequest(index, item.toString())));
        try {
            client.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: 搜索
     *
     * @param index   index
     * @param builder 查询参数
     * @param c       结果类对象
     * @return java.util.ArrayList
     */
    public <T> List<T> search(String index, SearchSourceBuilder builder, Class<T> c) {
        SearchRequest request = new SearchRequest(index);
        request.source(builder);
        try {
            SearchResponse response = client.search(request, RequestOptions.DEFAULT);
            SearchHit[] hits = response.getHits().getHits();
            List<T> res = new ArrayList<>(hits.length);
            for (SearchHit hit : hits) {
                res.add(JSON.parseObject(hit.getSourceAsString(), c));
            }
            System.err.println("查询符合满足条件的总条数=" + response.getHits().getTotalHits());
            return res;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 常用分页查询 from size
     *
     * @param index
     * @param page
     * @param builder
     * @param c
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> Page<T> searchPage(String index, Page page, SearchSourceBuilder builder, Class<T> c) throws IOException {
        builder.from((int) ((page.getCurrent() - 1) * page.getSize()));
        builder.size((int) page.getSize());
        SearchRequest searchRequest = new SearchRequest(index);
        searchRequest.source(builder);
        SearchResponse response = client.search(searchRequest, RequestOptions.DEFAULT);
        long totalCount = response.getHits().getTotalHits().value;
        page.setTotal(totalCount);
        //数据总页数
        SearchHit[] hits = response.getHits().getHits();
        List<ESSearchVo> res = new ArrayList<>(hits.length);
        for (SearchHit hit : hits) {
            ESSearchVo esSearchVo = new ESSearchVo();
            Map<String, HighlightField> highlightFields = hit.getHighlightFields();
            Collection<HighlightField> values = highlightFields.values();
            ArrayList<MyHighlightField> myHighlightFields = new ArrayList<>(values.size());
            for (HighlightField value : values) {
                String name = value.getName();
                String content = Arrays.toString(value.getFragments());
                myHighlightFields.add(new MyHighlightField(name, content));
            }
            esSearchVo.setMyHighlightFields(myHighlightFields);
            esSearchVo.setT(JSON.parseObject(hit.getSourceAsString(), c));
            res.add(esSearchVo);
        }
        page.setRecords(res);
        return page;
    }

    /**
     * Description: 删除index
     *
     * @param index index
     * @return void
     */
    public void deleteIndex(String index) {
        try {
            client.indices().delete(new DeleteIndexRequest(index), RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Description: delete by query
     *
     * @param index   index
     * @param builder builder
     */
    public void deleteByQuery(String index, QueryBuilder builder) {
        DeleteByQueryRequest request = new DeleteByQueryRequest(index);
        request.setQuery(builder);
        //设置批量操作数量,最大为10000
        request.setBatchSize(10000);
        request.setConflicts("proceed");
        try {
            client.deleteByQuery(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}