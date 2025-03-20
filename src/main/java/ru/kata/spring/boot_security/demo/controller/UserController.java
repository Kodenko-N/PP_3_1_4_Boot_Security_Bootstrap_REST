package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/addNewUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/saveUser")
    public String create(@ModelAttribute("user") User user) {
        System.out.println("Controller: saving" + user);
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/updateInfo")
    public String updateUser(@RequestParam("userId") int id, Model model) {
        System.out.println("Controller get user update with user" + userService.getUserById(id).toString() );
        model.addAttribute("user", userService.getUserById(id));
        return "addUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        System.out.println("Controller post user update with user" + user.toString() );
        userService.update(user);
        return "redirect:/users";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}
