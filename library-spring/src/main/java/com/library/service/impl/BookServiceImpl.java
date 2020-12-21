package com.library.service.impl;

import com.library.dao.BookDao;
import com.library.dao.UserDao;
import com.library.po.Book;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookDao;
    @Override
    public List<Book> findAllBook(){
        return this.bookDao.findAllBook();
    }

    @Override
    public List<Book> queryBook(Book book){
        return this.bookDao.queryBook(book);
    }

    @Override
    public Book checkISBN(String isbn){
        return this.bookDao.checkISBN(isbn);
    }

    @Override
    public int addBook(Book book){
        return this.bookDao.addBook(book);
    }

    @Override
    public int updateBook(Book book){
        return this.bookDao.updateBook(book);
    }
    @Override
    public int deleteBook(String isbn){
        return this.bookDao.deleteBook(isbn);
    }

}
