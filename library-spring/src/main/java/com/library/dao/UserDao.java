package com.library.dao;

import com.library.po.User;

import java.util.List;


public interface UserDao {
    public List<User> findAllUser();
    public User findUserByCardNum(String cardnum);
    public List<User> findUserByUserName(String username);
    public List<User> findUserByUserSex(String usersex);
    public List<User> findUserByUserDepa(String userdepa);
    public List<User> findUserByUserClass(String userclass);
    public List<User> findUserByGrade(String grade);

}
