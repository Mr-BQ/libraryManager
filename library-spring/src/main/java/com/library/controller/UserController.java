package com.library.controller;

import com.library.po.User;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findAllUser")
    @ResponseBody
    public List<User> findAllUser(){
        List<User> users= userService.findAllUser();
        return users;
    }

    @RequestMapping("/findUserByCardNum")
    @ResponseBody
    public User findUserByCardNum(String cardnum){
        User user = userService.findUserByCardNum(cardnum);
        return user;
    }


    @RequestMapping("/findUserByUserName")
    @ResponseBody
    public List<User> findUserByUserName(String username){
        List<User> users = userService.findUserByUserName(username);
        return users;
    }

    @RequestMapping("/findUserByUserSex")
    @ResponseBody
    public List<User> findUserByUserSex(String usersex){
        List<User> users = userService.findUserByUserSex(usersex);
        return users;
    }

    @RequestMapping("/findUserByUserDepa")
    @ResponseBody
    public List<User> findUserByUserDepa(String userdepa){
        List<User> users = userService.findUserByUserDepa(userdepa);
        return users;
    }

    @RequestMapping("/findUserByUserClass")
    @ResponseBody
    public List<User> findUserByUserClass(String userclass) {
        List<User> users = userService.findUserByUserClass(userclass);
        return users;
    }

    @RequestMapping("/findUserByGrade")
    @ResponseBody
    public List<User> findUserByGrade(String grade){
        List<User> users = userService.findUserByGrade(grade);
        return users;
    }

    @RequestMapping("/checkin")
    @ResponseBody
    public String checkin(String username,String password){
        System.out.println("username = " + username + " , password = " + password);
        return "yes";
    }
}
