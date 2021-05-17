package com.example.restfulwebservice.user.repository;

import com.example.restfulwebservice.user.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

//@Component
public class UserRepositoryMemory {

//    private static List<User> users = new ArrayList<>();
//
//    static {
//        users.add(new User((long) 1,"ljw",new Date(),"001","950000-0000000"));
//        users.add(new User((long) 2,"kty",new Date(),"002","950000-0000000"));
//        users.add(new User((long) 3,"kji",new Date(),"003","950000-0000000"));
//    }
//
//
//    @Override
//    public List<User> selectAll() {
//        return users;
//    }
//
//    @Override
//    public User select(long id) {
//
//        for (User user: users)
//            if (user.getId()==id)
//                return user;
//
//        return null;
//    }
//
//    @Override
//    public User insert(User user) {
//        if (user.getId()==null)
//            user.setId((long) (users.size()+1));
//        if (user.getJoinDate()==null)
//            user.setJoinDate(new Date());
//        users.add(user);
//        return user;
//    }
//
//    @Override
//    public User delete(long id) {
//        Iterator<User> iter = users.iterator();
//        while (iter.hasNext()) {
//            User user = iter.next();
//            if (user.getId() == id) {
//                iter.remove();
//                return user;
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public User update(User user) {
//        if (user == null) return null;
//
//        Iterator<User> iter = users.iterator();
//        while (iter.hasNext()) {
//            User next = iter.next();
//            if (next.getId() == user.getId()) {
//                next.setJoinDate(next.getJoinDate());
//                next.setName(next.getName());
//                break;
//            }
//        }
//        return user;
//    }
}
