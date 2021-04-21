package com.example.restfulwebservice.user.dao;

import com.example.restfulwebservice.user.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserDAOMemory implements UserDAO {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User(1,"ljw",new Date()));
        users.add(new User(2,"kty",new Date()));
        users.add(new User(3,"kji",new Date()));
    }


    @Override
    public List<User> find() {
        return users;
    }

    @Override
    public User find(long id) {

        for (User user: users)
            if (user.getId()==id)
                return user;

        return null;
    }

    @Override
    public User save(User user) {
        if (user.getId()==null)
            user.setId((long) (users.size()+1));
        users.add(user);
        return user;
    }
}
