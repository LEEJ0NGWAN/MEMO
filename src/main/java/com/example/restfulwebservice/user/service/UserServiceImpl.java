package com.example.restfulwebservice.user.service;

import com.example.restfulwebservice.user.User;
import com.example.restfulwebservice.user.dao.UserDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @Override
    public List<User> findAll() {
        return userDAO.selectAll();
    }

    @Override
    public User find(Long id) {
        return userDAO.select(id);
    }

    @Override
    public User save(User user) {
        return userDAO.insert(user);
    }

    @Override
    public User delete(Long id) {
        return userDAO.delete(id);
    }

    @Override
    public User modify(User user) {
        return userDAO.update(user);
    }
}
