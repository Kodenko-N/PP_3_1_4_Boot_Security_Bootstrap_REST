package ru.kata.spring.boot_security.demo.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

public interface UserService extends UserDetailsService {
   List<User> getAllUsers();

    User getUserByName(String name);

    User getUserByEmail(String name);

    User getUserById(long id);

    void save(User user);

    User update(User user);

    void delete(long id);
}
