package com.example.task1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.example.task1.client")
@SpringBootApplication
public class Task1Application {

    public static void main(String[] args) {
        SpringApplication.run(Task1Application.class, args);
    }
}