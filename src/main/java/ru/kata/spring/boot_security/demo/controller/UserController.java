package ru.kata.spring.boot_security.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.dao.RolesDaoImp;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RolesDaoImp rolesDaoImp;

    public UserController(UserService userService, RolesDaoImp rolesDaoImp) {
        this.userService = userService;
        this.rolesDaoImp = rolesDaoImp;
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        /*
        Role admin = new Role();
        admin.setRoleName("admin");
        admin.setDescription("admin");
        Role user = new Role();
        user.setRoleName("user");
        user.setDescription("user");
        User adminUser = new User("admin","admin","admin",admin);
        User userUser = new User("user","user","user",user);
        create(adminUser);
        create(userUser);
        System.out.println("Users are been loaded");
        */
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/addNewUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", rolesDaoImp.getAllRoles());
        return "addUser";
    }

    @PostMapping("/saveUser")
    public String create(@ModelAttribute("user") User user) {
        System.out.println("Controller: saving" + user);
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/updateInfo")
    public String updateUser(@RequestParam("userId") int id, Model model) {
        System.out.println("Controller get user update with user" + userService.getUserById(id).toString() );
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", rolesDaoImp.getAllRoles());
        return "addUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        System.out.println("Controller post user update with user" + user.toString() );
        userService.update(user);
        return "redirect:/admin";
    }

    @RequestMapping("/deleteUser")
    public String deleteUser(@RequestParam("userId") int id) {
        userService.delete(id);
        return "redirect:/admin";
    }

    @GetMapping("/403")
    public String accessDenied() {
        return "403"; // Страница ошибки доступа
    }

    @GetMapping("/user")
    public String showUser() {
        return "user";
    }
}
