package com.xrt.bzj.dao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据存储对象
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElasticEntity<T> {
    /**
     * 主键标识，用户ES持久化
     */
    private String id;

    /**
     * JSON对象，实际存储数据
     */
    private T data;
}
