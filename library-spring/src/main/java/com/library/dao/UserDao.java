package com.library.dao;

import com.library.po.User;


public interface UserDao {
    public User findUserByCardNum(String cardnum);
}
