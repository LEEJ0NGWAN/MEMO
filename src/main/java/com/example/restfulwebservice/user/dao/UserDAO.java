package com.example.restfulwebservice.user.dao;

import com.example.restfulwebservice.user.User;

import java.util.List;

public interface UserDAO {

    public List<User> selectAll();
    public User select(long id);
    public User insert(User user);
}
