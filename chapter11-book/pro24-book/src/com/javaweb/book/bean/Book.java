package com.javaweb.book.bean;

/**
 * ClassName: Book
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/20 21:51
 * @Version 1.0
 */
public class Book {
    private Integer id;
    private String bookImg;
    private String bookName;
    private Double price;
    private User author;
    private Integer saleCount;
    private Integer bookCount;
    private Integer bookStatus = 0;
}
