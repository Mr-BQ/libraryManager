package com.library.service;

import com.library.po.Book;

import java.util.List;

public interface BookService {
    public List<Book> findAllBook();
    public List<Book> queryBook(Book book);
    public Book checkISBN(String isbn);
    public int addBook(Book book);
    public int updateBook(Book book);
    public int deleteBook(String isbn);
}
