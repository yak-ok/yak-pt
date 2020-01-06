package com.yak.dao;

import com.yak.model.User;

import java.util.List;

public interface UserDao {

    int insert(User user);

    List<User> selectUsers();
}
