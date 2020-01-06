package com.yak.service;

import com.github.pagehelper.PageInfo;
import com.yak.model.User;

public  interface UserService {
    int addUser(User user);

    PageInfo<User> findUsers(int pageNum, int pageSize);
}
