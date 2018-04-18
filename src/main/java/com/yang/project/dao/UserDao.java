package com.yang.project.dao;

import com.yang.project.model.Follow;
import com.yang.project.model.Letter;
import com.yang.project.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    User login(@Param("code")String code,
               @Param("pwd")String pwd);

    Long update(User user);

    Long register(@Param("code") String code,
                  @Param("pwd") String pwd,
                  @Param("regTime") String regTime);

    Integer findMaxId();

    Long attention(Follow follow);
    Long cancelAttention(Follow follow);

    List<User> getAttention(@Param("userId") String userId);

    Long sendLetter(Letter letter);
}
