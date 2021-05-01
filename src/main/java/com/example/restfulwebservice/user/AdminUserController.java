package com.example.restfulwebservice.user;

import com.example.restfulwebservice.user.exception.UserNotFoundException;
import com.example.restfulwebservice.user.service.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import javax.xml.ws.Response;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/admin", produces = "application/json")
public class AdminUserController {

    private UserService userService;

    @Autowired
    public AdminUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(path = "/users")
    public ResponseEntity<MappingJacksonValue> retrieveAllUsers() {

        List<User> list = userService.findAll();

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joinDate","password","ssn");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo", simpleBeanPropertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);
        mappingJacksonValue.setFilters(filterProvider);

        return ResponseEntity.ok(mappingJacksonValue);
    }

    @GetMapping(path = "/users/{id}")
    public ResponseEntity<MappingJacksonValue> retrieveUser(@PathVariable Long id) {
        User user = userService.find(id);
        if (user == null)
            throw new UserNotFoundException(String.format("id=%d not found",id));

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joinDate","password","ssn");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfo", simpleBeanPropertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(user);
        mappingJacksonValue.setFilters(filterProvider);

        return ResponseEntity.ok(mappingJacksonValue);
    }
}
