package com.example.restfulwebservice.hello;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    /**
     * [GET] hello
     */
    // RequestMapping(mehod=RequestMethod.GET, path="/hello")
    @GetMapping(path="/hello")
    public String hello() {
        return "hello";
    }

    /**
     * [GET] hello-bean
     * @return
     */
    @GetMapping(path="/hello-bean")
    public HelloBean helloBean() {
        return new HelloBean("hihi");
    }
}
