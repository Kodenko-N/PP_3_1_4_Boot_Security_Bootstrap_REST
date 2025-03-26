package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.awt.*;
import java.net.URI;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
        String url = "http://localhost:8080/login";
        System.out.println("Для начала работы перейдите по ссылке: " + url);

    }
}
