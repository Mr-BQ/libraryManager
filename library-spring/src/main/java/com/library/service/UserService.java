package com.library.service;

import com.library.po.User;

public interface UserService {
    public User findUserByCardNum(String cardnum);
}
