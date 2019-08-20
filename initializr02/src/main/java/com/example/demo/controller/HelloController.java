package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author likeguo
 * @since 2019-08-20 14:28
 */
@RestController
public class HelloController {

    @RequestMapping("/test")
    public String getTest(){
        return "test is OK!";
    }

}
