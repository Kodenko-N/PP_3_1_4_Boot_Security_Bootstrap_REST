package ru.kata.spring.boot_security.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.dao.UserDao;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.*;

@Service
public class UserServiceImp implements UserService {

    private UserDao userDao;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Autowired
    public void setPasswordEncoder(@Lazy PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserByName(String name) { return userDao.getUserByName(name); }

    @Override
    @Transactional(readOnly = true)
    public User getUserByEmail(String email) { return userDao.getUserByEmail(email); }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void save(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userDao.save(user);
    }

    @Override
    @Transactional
    public void update(User user) {
        if (user.getId() == null || !Objects.equals(user.getPassword(), "") ) {
            System.out.println("Encoding new password");
            user.setPassword(passwordEncoder.encode(user.getPassword()));
        } else {
            System.out.println("Password is NOT changed.");
            user.setPassword(getUserById(user.getId()).getPassword()); //keep the same password for existing user
        }
        if (user.getRoles() == null || user.getRoles().isEmpty()) {
            System.out.println("User " + user.getName() + " has no roles, USER role pre-defined ");
            user.addRole(new Role("USER"));
        }
        userDao.update(user);
    }
    @Override
    @Transactional
    public void delete(long id) {
        userDao.delete(id);
    }

    //UserDetailsService implementation. Username = email (!)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = getUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found with email: " + email);
        }
        return new org.springframework.security.core.userdetails.User(
                user.getName(),
                user.getPassword(),
                user.getRoles());
    }
}
