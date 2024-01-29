package com.niksde.springboot.cruddemo.dao;

import com.niksde.springboot.cruddemo.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
}
