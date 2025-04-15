//package ru.kata.spring.boot_security.demo.controller;
//
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.RoleService;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//@Controller
//@RequestMapping("/")
//public class UserController_orig {
//
//    private final UserService userService;
//    private final RoleService rolesService;
//
//    public UserController_orig(UserService userService, RoleService rolesService) {
//        this.userService = userService;
//        this.rolesService = rolesService;
//    }
//
//    @GetMapping("/login") //Страница входа
//    public String showLoginPage() {
//        return "login";
//    }
//
//    @GetMapping("/admin") //Основная страница админа
//    public String showAllUsers(Model model, @AuthenticationPrincipal UserDetails userDetails) {
//        model.addAttribute("users", userService.getAllUsers());
//        model.addAttribute("roles", rolesService.getAllRoles());
//        model.addAttribute("user", new User());
//        model.addAttribute("currentUser", userService.getUserByName(userDetails.getUsername()) );
//        return "admin";
//    }
//
//    @PostMapping("/updateUser") //Создание и изменение пользователя
//    public String updateUser(@ModelAttribute("user") User user) {
//        System.out.println("Controller post user update with user" + user.toString() );
//        userService.update(user);
//        return "redirect:/admin";
//    }
//
//    @RequestMapping("/deleteUser") //удаление пользователя
//    public String deleteUser(@RequestParam("userId") Long id) {
//        System.out.println("Controller delete user with id " + id);
//        userService.delete(id);
//        return "redirect:/admin";
//    }
//
//    @GetMapping("/403")// Страница ошибки доступа
//    public String accessDenied() {
//        return "403";
//    }
//
//    @GetMapping("/user") //Страница пользователя
//    public String showUser(Model model, @AuthenticationPrincipal UserDetails userDetails ) {
//        model.addAttribute("currentUser", userService.getUserByName(userDetails.getUsername()) );
//        return "user";
//    }
//}
