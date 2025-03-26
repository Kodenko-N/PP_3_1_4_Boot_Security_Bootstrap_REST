package ru.kata.spring.boot_security.demo.controller;

import jakarta.annotation.PostConstruct;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.RoleService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService rolesService;

    public UserController(UserService userService, RoleService rolesService) {
        this.userService = userService;
        this.rolesService = rolesService;
    }

    //Инициализация стартовых учетных записей
    @PostConstruct
    public void init() {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        userService.save(new User("admin","ALMIGHTY"
                ,passwordEncoder.encode("admin"),new Role("ADMIN","ADMIN")));
        userService.save(new User("user","RESTRICTED"
                ,passwordEncoder.encode("user"),new Role("USER","USER")));
    }

    @GetMapping("/admin")
    public String showAllUsers(Model model) {
        List<User> users = userService.getAllUsers();
        model.addAttribute("users", users);
        return "admin";
    }

    @GetMapping("/addNewUser")
    public String addUser(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("roles", rolesService.getAllRoles());
        return "addUser";
    }

    @PostMapping("/saveUser")
    public String create(@ModelAttribute("user") User user) {
        System.out.println("Controller: saving" + user);
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/updateInfo")
    public String updateUser(@RequestParam("userId") int id, Model model) {
        System.out.println("Controller get user update with user" + userService.getUserById(id).toString() );
        model.addAttribute("user", userService.getUserById(id));
        model.addAttribute("roles", rolesService.getAllRoles());
        return "addUser";
    }

    @PostMapping("/updateUser")
    public String updateUser(@ModelAttribute("user") User user) {
        System.out.println("Controller post user update with user" + user.toString() );
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
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
    public String showUser(Model model, @AuthenticationPrincipal UserDetails userDetails ) {
        model.addAttribute("user", userService.getUserByName(userDetails.getUsername()));
        return "user";
    }
}
