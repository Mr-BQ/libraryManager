package com.library.controller;


import com.library.po.Borrow;
import com.library.po.BorrowRecord;
import com.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class BorrowController {
    @Autowired
    BorrowService borrowService;

    @RequestMapping("/findAllBorrow")
    @ResponseBody
    public List<Borrow> findAllBorrow(){
        return this.borrowService.findAllBorrow();
    }

    @RequestMapping("/queryBorrow")
    @ResponseBody
    public List<Borrow> queryBorrow(Borrow borrow){
        System.out.println(borrow);
        return this.borrowService.queryBorrow(borrow);
    }

    @RequestMapping("/checkRemain")
    @ResponseBody
    public String checkRemain(String isbn){
        return this.borrowService.checkRemain(isbn);
    }

    @RequestMapping("/checkBorrownum")
    @ResponseBody
    public String checkBorrownum(String cardnum){
        return this.borrowService.checkBorrownum(cardnum);
    }

    @RequestMapping("/addBorrow")
    @ResponseBody
    public int addBorrow(Borrow borrow){
        BorrowRecord br = new BorrowRecord();
        Integer bookid = this.borrowService.getbookID(borrow.getBookISBN());
        Integer userid = this.borrowService.getuserID(borrow.getUserCardNum());
        SimpleDateFormat df = new SimpleDateFormat ("yyyy-MM-dd");
        br.setBookId(bookid);
        br.setUserId(userid);
        br.setBorrowDate(df.format(new Date()));
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.MONTH,1);
        br.setDateToReturn(df.format(cal.getTime()));
        int res = this.borrowService.addBorrow(br);
        if(res == 1){
            this.borrowService.remainSubone(bookid);
            this.borrowService.borrowNumSubone(userid);
        }
        return res;
    }

    @RequestMapping("/returnBook")
    @ResponseBody
    public int returnBook(Borrow borrow){
        int res = this.borrowService.queryBorrow(borrow).size();
        if(res == 0){
            return 0;
        }
        if(this.borrowService.queryBorrow(borrow).get(0).getReturnDate() != null){
            return -1;
        }
        Integer bookid = this.borrowService.getbookID(borrow.getBookISBN());
        Integer userid = this.borrowService.getuserID(borrow.getUserCardNum());
        BorrowRecord br = new BorrowRecord();
        br.setBookId(bookid);
        br.setUserId(userid);
        SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd");
        br.setReturnDate(sdf.format(new Date()));
        this.borrowService.returnBook(br);
        this.borrowService.remainAddone(this.borrowService.getbookID(borrow.getBookISBN()));
        this.borrowService.borrowNumAddone(this.borrowService.getuserID(borrow.getUserCardNum()));
        return 1;
    }


}
