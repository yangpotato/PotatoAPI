package com.yang.project.service;

import com.yang.project.model.User;

public interface UserService {
    User Login(String code, String pwd);
}
