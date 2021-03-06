package com.example.restfulwebservice.user.service;

import com.example.restfulwebservice.user.User;

import java.util.List;

public interface UserService {

    public List<User> findAll();
    public User find(Long id);
    public User save(User user);
    public void delete(Long id);
    public User modify(User user);
}
