package com.xrt.bzj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.xrt.bzj.dao.entity.Book;
import com.xrt.bzj.dao.mapper.BookMapper;
import com.xrt.bzj.dao.param.BookPageParam;
import com.xrt.bzj.service.BookService;
import io.geekidea.springbootplus.generator.common.service.impl.BaseServiceImpl;
import io.geekidea.springbootplus.generator.core.pagination.PageInfo;
import io.geekidea.springbootplus.generator.core.pagination.Paging;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 服务实现类
 *
 * @author lee
 * @since 2022-03-19
 */
@Slf4j
@Service
public class BookServiceImpl extends BaseServiceImpl<BookMapper, Book> implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveBook(Book book) throws Exception {
        return super.save(book);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateBook(Book book) throws Exception {
        return super.updateById(book);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean deleteBook(Long id) throws Exception {
        return super.removeById(id);
    }

    @Override
    public Paging<Book> getBookPageList(BookPageParam bookPageParam) throws Exception {
        Page<Book> page = new PageInfo<>(bookPageParam);
        LambdaQueryWrapper<Book> wrapper = new LambdaQueryWrapper<>();
        IPage<Book> iPage = bookMapper.selectPage(page, wrapper);
        return new Paging<Book>(iPage);
    }

}
