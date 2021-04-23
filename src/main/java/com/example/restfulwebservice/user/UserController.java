package com.example.restfulwebservice.user;

import com.example.restfulwebservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers() {
        return userService.findAll();
    }

    @GetMapping(path = "/users/{id}")
    public User retrieveUser(@PathVariable Long id) {
        return userService.find(id);
    }

    @PostMapping(path = "/users")
    public void createUser(@RequestBody User user) {
        User savedUser = userService.save(user);

    }

}
