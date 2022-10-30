package com.xrt.bzj.service.handle;

import com.xrt.bzj.dao.entity.Book;
import com.xrt.bzj.dao.entity.Order;
import com.xrt.bzj.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.service.handle
 * @date 2022/8/13 16:09
 */

@Component
public class EmailHandler extends MsgHandler {

    @Autowired
    OrderService orderService;

    @Override
    public void send() {
        Order order = orderService.getById(1);
        System.out.println(order);
        List<Book> list = bookService.list();
        System.out.println(list);
        System.out.println("发送消息");
    }
}
