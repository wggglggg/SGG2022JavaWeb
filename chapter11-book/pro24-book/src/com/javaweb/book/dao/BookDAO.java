package com.javaweb.book.dao;

import com.javaweb.book.bean.Book;
import com.javaweb.book.bean.User;

import java.util.List;

/**
 * ClassName: BookDAO
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 13:46
 * @Version 1.0
 */
public interface BookDAO {

    List<Book> getBookList();
}
