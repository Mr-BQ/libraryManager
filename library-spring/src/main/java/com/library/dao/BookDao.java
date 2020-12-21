package com.library.dao;

import com.library.po.Book;
import com.library.po.User;


import java.util.List;

public interface BookDao {
    public List<Book> findAllBook();
    public List<Book> queryBook(Book book);
    public Book checkISBN(String isbn);
    public int addBook(Book book);
    public int updateBook(Book book);
    public int deleteBook(String isbn);
}
