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


}
