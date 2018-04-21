package com.yang.project.service;

import com.yang.project.model.Follow;
import com.yang.project.model.Letter;
import com.yang.project.model.User;

import java.util.List;

public interface UserService {
    User Login(String code, String pwd);

    Long update(User user);

    Long register(String code, String pwd, String regTime);

    Integer findMaxId();

    Long attention(Follow follow);

    Long cancelAttention(Follow follow);

    List<User> getAttention(String userId);

    Long sendLetter(Letter letter);

    List<Letter> getLetter(String userId);
}
