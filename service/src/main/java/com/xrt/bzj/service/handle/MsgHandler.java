package com.xrt.bzj.service.handle;

import com.xrt.bzj.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.service.handle
 * @date 2022/8/13 16:08
 */
@Component
public abstract class MsgHandler {

    @Autowired
    protected BookService bookService;

    public abstract void send();
}
