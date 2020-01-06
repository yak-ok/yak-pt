package com.yak.dao;

import com.yak.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserDao {

    int insert(User user);

    List<User> selectUsers();
}
