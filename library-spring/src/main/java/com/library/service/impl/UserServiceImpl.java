package com.library.service.impl;

import com.library.dao.UserDao;
import com.library.po.Manager;
import com.library.po.User;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public List<User> findAllUser(){
        return this.userDao.findAllUser();
    }

    @Override
    public User findUserByCardNum(String cardnum){
        return this.userDao.findUserByCardNum(cardnum);
    }

    @Override
    public List<User> findUserByUserName(String username) {
        return this.userDao.findUserByUserName(username);
    }

    @Override
    public List<User> findUserByUserSex(String usersex) {
        return this.userDao.findUserByUserSex(usersex);
    }

    @Override
    public List<User> findUserByUserDepa(String userdepa) {
        return this.userDao.findUserByUserDepa(userdepa);
    }

    @Override
    public List<User> findUserByUserClass(String userclass) {
        return this.userDao.findUserByUserClass(userclass);
    }

    @Override
    public List<User> findUserByGrade(String grade) {
        return this.userDao.findUserByGrade(grade);
    }

    @Override
    public Manager checkin(String username, String password){
        return this.userDao.checkin(username,password);
    }

    @Override
    public int addUser(User user){
        return this.userDao.addUser(user);
    }

    @Override
    public int updateuser(User user){
        return this.userDao.updateuser(user);
    }

}
