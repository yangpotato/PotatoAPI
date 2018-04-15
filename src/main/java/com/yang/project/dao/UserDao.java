package com.yang.project.dao;

import com.yang.project.model.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserDao {
    User login(@Param("code")String code,
               @Param("pwd")String pwd);
}
