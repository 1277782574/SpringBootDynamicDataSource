package com.alivecaren.controller;

import com.alivecaren.model.User;
import com.alivecaren.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    @RequestMapping("/findCloud")
    public Object findCloudDataSource(){
        List<User> users=userService.findCloudDataSource();
        return users;
    }

    @RequestMapping("/findTest")
    public Object findTestDataSource(){
        List<User> users=userService.findTestDataSource();
        return users;
    }


}
