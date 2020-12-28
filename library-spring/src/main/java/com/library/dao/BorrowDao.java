package com.library.dao;

import com.library.po.Borrow;
import com.library.po.BorrowRecord;

import java.util.List;

public interface BorrowDao {
    public List<Borrow> findAllBorrow();
    public List<Borrow> queryBorrow(Borrow borrow);
    public String checkRemain(String isbn);
    public String checkBorrownum(String cardnum);
    public int addBorrow(BorrowRecord borrowRecord);
    public int getbookID(String isbn);
    public int getuserID(String cardnum);
    public int remainSubone(Integer id);
    public int borrowNumSubone(Integer id);
    public int remainAddone(Integer id);
    public int borrowNumAddone(Integer id);
    public int returnBook(BorrowRecord borrowRecord);
}
