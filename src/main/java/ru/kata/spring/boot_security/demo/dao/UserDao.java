package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    List<User> getAllUsers();

    User getUserById(long id);

     void save(User user);

    void update(User user);

    void delete(long id);

    User getUserByName(String username);

    User getUserByEmail(String username);
}
