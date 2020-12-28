package com.library.service.impl;

import com.library.dao.BookDao;
import com.library.dao.BorrowDao;
import com.library.po.Borrow;
import com.library.po.BorrowRecord;
import com.library.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BorrowServiceImpl implements BorrowService {
    @Autowired
    BorrowDao borrowDao;
    @Override
    public List<Borrow> findAllBorrow(){
        return this.borrowDao.findAllBorrow();
    }

    @Override
    public List<Borrow> queryBorrow(Borrow borrow){
        return this.borrowDao.queryBorrow(borrow);
    }

    @Override
    public String checkRemain(String isbn){
        return this.borrowDao.checkRemain(isbn);
    }

    @Override
    public String checkBorrownum(String cardnum){
        return this.borrowDao.checkBorrownum(cardnum);
    }

    @Override
    public int addBorrow(BorrowRecord borrowRecord){
        return this.borrowDao.addBorrow(borrowRecord);
    }

    @Override
    public int getbookID(String isbn){
        return this.borrowDao.getbookID(isbn);
    }

    @Override
    public int getuserID(String cardnum){
        return this.borrowDao.getuserID(cardnum);
    }

    @Override
    public int remainSubone(Integer id){
        return this.borrowDao.remainSubone(id);
    }

    @Override
    public int borrowNumSubone(Integer id){
        return this.borrowDao.borrowNumSubone(id);
    }

}
