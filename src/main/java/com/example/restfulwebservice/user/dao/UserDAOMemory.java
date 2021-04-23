package com.example.restfulwebservice.user.dao;

import com.example.restfulwebservice.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Component
public class UserDAOMemory implements UserDAO {

    private static List<User> users = new ArrayList<>();

    static {
        users.add(new User((long) 1,"ljw",new Date()));
        users.add(new User((long) 2,"kty",new Date()));
        users.add(new User((long) 3,"kji",new Date()));
    }


    @Override
    public List<User> selectAll() {
        return users;
    }

    @Override
    public User select(long id) {

        for (User user: users)
            if (user.getId()==id)
                return user;

        return null;
    }

    @Override
    public User insert(User user) {
        if (user.getId()==null)
            user.setId((long) (users.size()+1));
        if (user.getJoinDate()==null)
            user.setJoinDate(new Date());
        users.add(user);
        return user;
    }

    @Override
    public User delete(long id) {
        Iterator<User> iter = users.iterator();
        while (iter.hasNext()) {
            User user = iter.next();
            if (user.getId() == id) {
                iter.remove();
                return user;
            }
        }
        return null;
    }
}
