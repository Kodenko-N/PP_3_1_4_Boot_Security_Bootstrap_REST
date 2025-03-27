package ru.kata.spring.boot_security.demo.dao;

import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();

    public User getUserById(long id);

    public void save(User user);

    public void update(User user);

    public void delete(long id);

    User getUserByName(String username);
}
