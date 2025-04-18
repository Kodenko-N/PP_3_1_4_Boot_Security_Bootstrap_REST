package ru.kata.spring.boot_security.demo.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;
import ru.kata.spring.boot_security.demo.service.RoleService;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final RoleService rolesService;

    public UserController(UserService userService, RoleService rolesService) {
        this.userService = userService;
        this.rolesService = rolesService;
    }

    @GetMapping("/login") //Страница входа
    public String showLoginPage() {
        return "login";
    }

    @GetMapping("/admin") //Основная страница админа
    public String showAllUsers(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        return "admin";
    }

    @GetMapping("/403")// Страница ошибки доступа
    public String accessDenied() {
        return "403";
    }

    @GetMapping("/user") //Страница пользователя
    public String showUser(Model model, @AuthenticationPrincipal UserDetails userDetails ) {
        model.addAttribute("currentUser", userService.getUserByName(userDetails.getUsername()) );
        return "user";
    }
}
