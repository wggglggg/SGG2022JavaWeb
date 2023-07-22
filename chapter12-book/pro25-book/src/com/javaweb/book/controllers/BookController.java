package com.javaweb.book.controllers;

import com.javaweb.book.bean.Book;
import com.javaweb.book.bean.User;
import com.javaweb.book.service.BookService;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * ClassName: BookController
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/21 10:34
 * @Version 1.0
 */
public class BookController {

    private BookService bookService;

    public String index(HttpSession session){
        List<Book> bookList = bookService.getBookList();

        session.setAttribute("bookList", bookList);


        return "index";
    }
}
