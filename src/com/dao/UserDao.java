package com.dao;

import com.a_bean.User;

public interface UserDao {
    //通过用户ID查询一个用户
    public User selectUserById(Integer id);
}
