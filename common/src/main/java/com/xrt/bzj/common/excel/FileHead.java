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

    String key;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public FileHead() {
    }

    public FileHead(String name, String key) {
        this.name = name;
        this.key = key;
    }
}
