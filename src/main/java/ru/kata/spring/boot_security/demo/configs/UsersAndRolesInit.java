package ru.kata.spring.boot_security.demo.configs;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

@Component
public class UsersAndRolesInit {

    private final UserService userService;
    public UsersAndRolesInit(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        userService.save(new User("admin","ALMIGHTY", 40, "admin@mail.ru"
                ,"admin",new Role("ADMIN","ADMIN")));
        userService.save(new User("user","RESTRICTED", 18, "user@mail.ru"
                ,"user",new Role("USER","USER")));
    }
}
