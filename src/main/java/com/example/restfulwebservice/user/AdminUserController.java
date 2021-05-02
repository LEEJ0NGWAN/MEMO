package com.example.restfulwebservice.user;

import com.example.restfulwebservice.user.exception.UserNotFoundException;
import com.example.restfulwebservice.user.service.UserService;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.fasterxml.jackson.databind.util.BeanUtil;
import org.springframework.beans.BeanUtils;
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

//    @GetMapping(path = "/users/{id}", params = "version=1")
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1")
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json")
    public ResponseEntity<MappingJacksonValue> retrieveUserV1(@PathVariable Long id) {
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

//    @GetMapping(path = "/users/{id}/", params = "version=2")
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2")
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json")
    public ResponseEntity<MappingJacksonValue> retrieveUserV2(@PathVariable Long id) {
        User user = userService.find(id);
        if (user == null)
            throw new UserNotFoundException(String.format("id=%d not found",id));

        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user,userV2);
        userV2.setGrade("VIP");

        SimpleBeanPropertyFilter simpleBeanPropertyFilter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id","name","joinDate","password","ssn","grade");

        FilterProvider filterProvider = new SimpleFilterProvider().addFilter("UserInfoV2", simpleBeanPropertyFilter);

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(userV2);
        mappingJacksonValue.setFilters(filterProvider);

        return ResponseEntity.ok(mappingJacksonValue);
    }
}
