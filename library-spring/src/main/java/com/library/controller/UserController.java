package com.library.controller;

import com.library.po.User;
import com.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/findUserByCardNum")
    @ResponseBody
    public User findUserByCardNum(String cardnum){
        System.out.println("findUserByCardNum" + " cardnum " + cardnum);
        User user = userService.findUserByCardNum(cardnum);
        return user;
    }
}
