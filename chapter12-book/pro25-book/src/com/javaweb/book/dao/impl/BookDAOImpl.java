package com.javaweb.book.dao.impl;

import com.javaweb.base.BaseDAO;
import com.javaweb.book.bean.Book;
import com.javaweb.book.dao.BookDAO;
import org.junit.Test;

import java.util.List;

/**
 * ClassName: BookDAOImpl
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 13:48
 * @Version 1.0
 */
public class BookDAOImpl extends BaseDAO<Book> implements BookDAO {
    @Override
    public List<Book> getBookList() {
        String sql = "select * from t_book";
        List<Book> bookList = executeQuery(sql);
        return bookList;
    }

    @Override
    public Book getBook(Integer id) {
        String sql = "select * from t_book where id =?";
        return load(sql, id);
    }


}
