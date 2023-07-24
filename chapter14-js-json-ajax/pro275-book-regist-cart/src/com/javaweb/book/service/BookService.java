package com.javaweb.book.service;

import com.javaweb.book.bean.Book;

import java.util.List;

/**
 * ClassName: BookService
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 13:51
 * @Version 1.0
 */
public interface BookService {
    List<Book> getBookList();

    Book getBook(Integer id);
}
