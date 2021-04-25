package com.example.restfulwebservice.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloController {

    @Autowired
    private MessageSource messageSource;

    /**
     * [GET] hello
     */
    // RequestMapping(mehod=RequestMethod.GET, path="/hello")
    @GetMapping(path = "/hello")
    public String hello() {
        return "hello";
    }

    /**
     * [GET] hello-bean
     *
     * @return
     */
    @GetMapping(path = "/hello-bean")
    public HelloBean helloBean() {
        return new HelloBean("hihi");
    }

    /**
     * [GET] hello-path-var
     *
     * @param var
     * @return
     */
    @GetMapping(path = "/hello-path-var/{var}")
    public HelloBean helloBean(@PathVariable String var) { return  new HelloBean(var); }

    @GetMapping(path="/hello-international")
    public String international(@RequestHeader(name="Accept-Language", required = false) Locale locale) {
        return messageSource.getMessage("greeting.message",null,locale);
    }
}
