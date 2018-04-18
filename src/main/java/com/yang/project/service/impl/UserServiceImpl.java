package com.yang.project.service.impl;

import com.yang.project.dao.UserDao;
import com.yang.project.model.Follow;
import com.yang.project.model.Letter;
import com.yang.project.model.User;
import com.yang.project.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserDao userDao;

    @Override
    public User Login(String code, String pwd) {
        return userDao.login(code, pwd);
    }

    @Override
    public Long update(User user) {
        return userDao.update(user);
    }

    @Override
    public Long register(String code, String pwd, String regTime) {
        return userDao.register(code, pwd,regTime);
    }

    @Override
    public Integer findMaxId() {
        return userDao.findMaxId();
    }

    @Override
    public Long attention(Follow follow) {
        return userDao.attention(follow);
    }

    @Override
    public Long cancelAttention(Follow follow) {
        return userDao.cancelAttention(follow);
    }

    @Override
    public List<User> getAttention(String userId) {
        return userDao.getAttention(userId);
    }

    @Override
    public Long sendLetter(Letter letter) {
        return userDao.sendLetter(letter);
    }
}
