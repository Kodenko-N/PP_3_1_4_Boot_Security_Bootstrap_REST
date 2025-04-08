package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
        String url = "http://localhost:8080/login";
        System.out.println("Для начала работы перейдите по ссылке: " + url);
        System.out.println("Пользователи по умолчанию: ");
        System.out.println("Пользователь: admin@mail.ru, пароль: \"admin\"");
        System.out.println("Пользователь: user@mail.ru, пароль: \"user\"");
    }
}
