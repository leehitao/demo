package com.xrt.bzj.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xrt.bzj.dao.entity.Book;
import com.xrt.bzj.dao.entity.ElasticEntity;
import com.xrt.bzj.dao.param.BookPageParam;
import com.xrt.bzj.service.BookService;
import com.xrt.bzj.service.es.DemoEs;
import org.apache.commons.collections4.CollectionUtils;
import org.elasticsearch.index.query.DisMaxQueryBuilder;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

//@Service
public class SearchService {

    @Autowired
    private DemoEs demoEs;

    @Autowired
    private BookService bookService;//业务的service

    /**
     * 初始化ES查询数据
     *
     * @return
     */
    public int initEsData() {
        List<Book> books = bookService.list();
        if (!CollectionUtils.isEmpty(books)) {
            List<ElasticEntity> list = new ArrayList<>();
            books.forEach(item -> list.add(new ElasticEntity<>(item.getId().toString(), item)));
            demoEs.insertBatch(demoEs.INDEX_NAME, list);
        }
        return books.size();
    }

    public Page<Book> doSearch(BookPageParam bookPageParam) {
        Long pageSize = bookPageParam.getPageSize();
        Long pageIndex = bookPageParam.getPageIndex();
        Page page = new Page(pageIndex, pageSize);
        Page<Book> bookPage = null;
        try {
            // 查询设置
            MatchQueryBuilder nameMatchQuery = QueryBuilders.matchQuery("name", bookPageParam.getKeyword());
            MatchQueryBuilder descriptionMatchQuery = QueryBuilders.matchQuery("description", bookPageParam.getKeyword());
            DisMaxQueryBuilder disMaxQueryBuilder = new DisMaxQueryBuilder();
            disMaxQueryBuilder.add(nameMatchQuery);
            disMaxQueryBuilder.add(descriptionMatchQuery);
            disMaxQueryBuilder.tieBreaker(0.3f);

            // 高亮设置
            HighlightBuilder highlightBuilder = new HighlightBuilder();
            highlightBuilder.field("name", 5, 1);
            highlightBuilder.field("description", 5, 1);
            highlightBuilder.preTags("<span style='color:red'>");
            highlightBuilder.postTags("</span>");

            SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
            searchSourceBuilder.query(disMaxQueryBuilder);
            searchSourceBuilder.highlighter(highlightBuilder);

            bookPage = demoEs.searchPage(demoEs.INDEX_NAME, page, searchSourceBuilder, Book.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bookPage;
    }
}

