package com.xrt.bzj.service;

import com.xrt.bzj.dao.entity.Book;
import com.xrt.bzj.dao.param.BookPageParam;
import io.geekidea.springbootplus.generator.common.service.BaseService;
import io.geekidea.springbootplus.generator.core.pagination.Paging;

/**
 * 服务类
 *
 * @author lee
 * @since 2022-03-19
 */
public interface BookService extends BaseService<Book> {

    /**
     * 保存
     *
     * @param book
     * @return
     * @throws Exception
     */
    boolean saveBook(Book book) throws Exception;

    /**
     * 修改
     *
     * @param book
     * @return
     * @throws Exception
     */
    boolean updateBook(Book book) throws Exception;

    /**
     * 删除
     *
     * @param id
     * @return
     * @throws Exception
     */
    boolean deleteBook(Long id) throws Exception;


    /**
     * 获取分页对象
     *
     * @param bookQueryParam
     * @return
     * @throws Exception
     */
    Paging<Book> getBookPageList(BookPageParam bookPageParam) throws Exception;

}
