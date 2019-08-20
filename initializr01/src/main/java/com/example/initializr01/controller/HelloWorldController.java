package com.example.initializr01.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author likeguo
 * @since 2019-08-20 11:34
 */
@RestController
public class HelloWorldController {

    @RequestMapping("/hello")
    public String HelloWorld(){
        return "Hello initializr01";
    }
}
