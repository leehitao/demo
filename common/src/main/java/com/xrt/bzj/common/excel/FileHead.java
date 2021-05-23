package com.xrt.bzj.common.excel;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.common.excel
 * @date 2021/5/23 18:24
 */
public class FileHead {

    String name;

    String indexName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIndexName() {
        return indexName;
    }

    public void setIndexName(String indexName) {
        this.indexName = indexName;
    }

    public FileHead() {
    }

    public FileHead(String name, String indexName) {
        this.name = name;
        this.indexName = indexName;
    }
}
