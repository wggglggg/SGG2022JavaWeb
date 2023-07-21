package com.javaweb.book.service.impl;

import com.javaweb.book.bean.Book;
import com.javaweb.book.dao.BookDAO;
import com.javaweb.book.service.BookService;

import java.util.List;

/**
 * ClassName: BookServiceImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 13:51
 * @Version 1.0
 */
public class BookServiceImpl implements BookService {

    private BookDAO bookDAO;
    @Override
    public List<Book> getBookList() {
        return bookDAO.getBookList();
    }
}
