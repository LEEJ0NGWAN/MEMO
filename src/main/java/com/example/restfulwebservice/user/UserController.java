package com.example.restfulwebservice.user;

import com.example.restfulwebservice.user.exception.UserNotFoundException;
import com.example.restfulwebservice.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(produces = "application/json")
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
    public ResponseEntity<User> retrieveUser(@PathVariable Long id) {
        User user = userService.find(id);
        if (user == null)
            throw new UserNotFoundException(String.format("id=%d not found",id));

        return ResponseEntity.ok(user);
    }

    @PostMapping(path = "/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {

        User savedUser = userService.save(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUser(@PathVariable Long id) {
        User user = userService.delete(id);

        if (user == null)
            throw new UserNotFoundException(String.format("id=%d not found",id));

    }

    @PutMapping(path = "/users")
    public ResponseEntity<User> modifyUser(@RequestBody User user) {

        User modifiedUser = userService.modify(user);

        if (modifiedUser == null)
            throw new UserNotFoundException(String.format("id=%d not found",user.getId()));

        return new ResponseEntity(user, HttpStatus.OK);
    }
}
