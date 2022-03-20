package com.xrt.bzj.service.es;

import lombok.Data;

import java.util.List;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.dao.vo
 * @date 2022/3/20 12:36
 */
@Data
public class ESSearchVo<T> {

    private T t;

    private List<MyHighlightField> myHighlightFields;
}
