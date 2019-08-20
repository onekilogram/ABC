package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author likeguo
 * @since 2019-08-20 14:25
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan
public class StartUp {
    public static void main(String[] args) {
        SpringApplication.run(StartUp.class,args);
    }
}


