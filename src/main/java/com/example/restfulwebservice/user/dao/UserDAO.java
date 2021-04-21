package com.example.restfulwebservice.user.dao;

import com.example.restfulwebservice.user.User;

import java.util.List;

public interface UserDAO {

    public List<User> find();
    public User find(long id);
    public User save(User user);
}
