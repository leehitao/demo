package com.xrt.bzj.common.excel;

import java.util.List;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.common.excel
 * @date 2021/5/23 18:22
 */
public class FileContainer<T> {

    String filePath;

    String fileName;

    List<FileHead> heads;

    List<T> data;

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public List<FileHead> getHeads() {
        return heads;
    }

    public void setHeads(List<FileHead> heads) {
        this.heads = heads;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
