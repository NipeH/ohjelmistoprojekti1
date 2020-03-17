package com.example.ohjelmistoprojekti1.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HelloResource {

    @RequestMapping({"/hello"})
    public String hello(){
        return "Hello World, Authentication works";
    }
}
