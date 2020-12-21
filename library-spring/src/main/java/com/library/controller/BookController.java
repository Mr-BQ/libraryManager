package com.library.controller;

import com.library.po.Book;
import com.library.po.User;
import com.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class BookController {
    @Autowired
    BookService bookService;

    @RequestMapping("/findAllBook")
    @ResponseBody
    public List<Book> findAllBook(){
        List<Book> books= bookService.findAllBook();
        return books;
    }

    @RequestMapping("/queryBook")
    @ResponseBody
    public List<Book> queryBook(Book book){
        System.out.println(book);
        return this.bookService.queryBook(book);
    }

    @RequestMapping("/checkISBN")
    @ResponseBody
    public String checkISBN(String isbn){
        return this.bookService.checkISBN(isbn) == null?"no":"yes";
    }

    @RequestMapping("/addBook")
    @ResponseBody
    public int addBook(Book book){
        System.out.println(book);
        return this.bookService.addBook(book);
    }

    @RequestMapping("/findBookByISBN")
    @ResponseBody
    public Book findBookByISBN(String isbn){
        return this.bookService.checkISBN(isbn);
    }

    @RequestMapping("/updateBook")
    @ResponseBody
    public int updateBook(Book book){
        return this.bookService.updateBook(book);
    }

    @RequestMapping("/deleteBook")
    @ResponseBody
    public int deleteBook(String isbn){
        return this.bookService.deleteBook(isbn);
    }
}
