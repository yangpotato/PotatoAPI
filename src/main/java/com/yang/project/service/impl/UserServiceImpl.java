package com.yang.project.service.impl;

import com.yang.project.dao.UserDao;
import com.yang.project.model.User;
import com.yang.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User Login(String code, String pwd) {
        return userDao.login(code, pwd);
    }
}
