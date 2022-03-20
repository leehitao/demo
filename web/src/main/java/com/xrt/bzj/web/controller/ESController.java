package com.xrt.bzj.web.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xrt.bzj.dao.entity.Book;
import com.xrt.bzj.dao.param.BookPageParam;
import com.xrt.bzj.service.impl.SearchService;
import io.geekidea.springbootplus.generator.common.api.ApiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Lee
 * @version 1.0
 * @project demo
 * @package com.xrt.bzj.web.controller
 * @date 2022/3/19 23:08
 */
@Controller
@RequestMapping("/es")
public class ESController {

    @Autowired
    SearchService searchService;

    @ResponseBody
    @PostMapping("/initEsData")
    public ApiResult initEsData() {
        int total = searchService.initEsData();
        return ApiResult.ok("ES索引初始化成功，共 " + total + " 条记录！");
    }


    @ResponseBody
    @PostMapping("/search")
    public ApiResult doSearch(HttpServletRequest request, @RequestBody BookPageParam bookPageParam) {
        Page<Book> bookPage = searchService.doSearch(bookPageParam);
        return ApiResult.ok(bookPage);

    }
}
