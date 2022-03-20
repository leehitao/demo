package com.xrt.bzj.service.es;

import lombok.Data;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.service.es
 * @date 2022/3/20 13:03
 */
@Data
public class MyHighlightField {

    private String name;

    private String content;

    public MyHighlightField() {
    }

    public MyHighlightField(String name, String content) {
        this.name = name;
        this.content = content;
    }
}
