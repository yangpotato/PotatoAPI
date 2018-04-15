package com.yang.project.controller;

import com.yang.project.model.User;
import com.yang.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserService service;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public User login(@RequestParam("code") String code,
                      @RequestParam("pwd") String pwd){
        User user = service.Login(code, pwd);
        return user;
    }



}
