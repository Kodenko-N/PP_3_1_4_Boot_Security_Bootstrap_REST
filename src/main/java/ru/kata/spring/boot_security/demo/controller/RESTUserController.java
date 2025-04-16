//package ru.kata.spring.boot_security.demo.controller;
//
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.core.userdetails.UserDetails;
//
//import org.springframework.web.bind.annotation.*;
//import ru.kata.spring.boot_security.demo.DTO.RoleDTO;
//import ru.kata.spring.boot_security.demo.DTO.UserDTO;
//import ru.kata.spring.boot_security.demo.model.Role;
//import ru.kata.spring.boot_security.demo.model.User;
//import ru.kata.spring.boot_security.demo.service.RoleService;
//import ru.kata.spring.boot_security.demo.service.UserService;
//
//import java.util.ArrayList;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Set;
//
//@RestController
//@RequestMapping("/api")
//public class RESTUserController {
//
//    private final UserService userService;
//    private final RoleService rolesService;
//
//    public RESTUserController(UserService userService, RoleService rolesService) {
//        this.userService = userService;
//        this.rolesService = rolesService;
//    }
//
////    @GetMapping("/login") //Страница входа
////    public String showLoginPage() {
////        return "login";
////    }
//
//    @GetMapping("/userlist")
//    public ResponseEntity<?> showAllUsers() {
//        List<User> users = userService.getAllUsers();
//        List<UserDTO> userDTOs = new ArrayList<>();
//        for (User user : users) {
//            userDTOs.add(new UserDTO(user.getId(), user.getName(), user.getSureName()
//                    , user.getAge(), user.getEmail(), user.getRoles()));
//        }
//        return ResponseEntity.ok(userDTOs);
//    }
//
//    @GetMapping("/rolelist")
//    public ResponseEntity<?> showAllRoles() {
//        Set<Role> roles = rolesService.getAllRoles();
//        Set<RoleDTO> roleDTOs = new HashSet<>();
//        for (Role role : roles) {
//            roleDTOs.add(new RoleDTO(role.getId(), role.getRoleName(), role.getDescription(), role.getUsers()));
//        }
//        return ResponseEntity.ok(roleDTOs);
//    }
//
//    @GetMapping("/currentuser")
//    public ResponseEntity<?> currentUser(@AuthenticationPrincipal UserDetails userDetails) {
//        User user = userService.getUserByName(userDetails.getUsername());
//        UserDTO currentUserDTO = new UserDTO(user.getId(), user.getName(), user.getSureName()
//                , user.getAge(), user.getEmail(), user.getRoles());
//        return ResponseEntity.ok(currentUserDTO);
//    }
//
//    @PostMapping("/updateuser")
//    public ResponseEntity<User> updateUser(@RequestBody User user) {
//        System.out.println("Update user API working with" + user);
//        User newUser = userService.update(user);
//        return ResponseEntity.ok(newUser);
//    }
//
////
////    @GetMapping("/admin") //Основная страница админа
////    public String showAllUsers(Model model, @AuthenticationPrincipal UserDetails userDetails) {
////        model.addAttribute("users", userService.getAllUsers());
////        model.addAttribute("roles", rolesService.getAllRoles());
////        model.addAttribute("user", new User());
////        model.addAttribute("currentUser", userService.getUserByName(userDetails.getUsername()) );
////        return "admin";
////    }
////
////    @PostMapping("/updateUser") //Создание и изменение пользователя
////    public String updateUser(@ModelAttribute("user") User user) {
////        System.out.println("Controller post user update with user" + user.toString() );
////        userService.update(user);
////        return "redirect:/admin";
////    }
////
////    @RequestMapping("/deleteUser") //удаление пользователя
////    public String deleteUser(@RequestParam("userId") Long id) {
////        System.out.println("Controller delete user with id " + id);
////        userService.delete(id);
////        return "redirect:/admin";
////    }
////
////    @GetMapping("/403")// Страница ошибки доступа
////    public String accessDenied() {
////        return "403";
////    }
////
////    @GetMapping("/user") //Страница пользователя
////    public String showUser(Model model, @AuthenticationPrincipal UserDetails userDetails ) {
////        model.addAttribute("currentUser", userService.getUserByName(userDetails.getUsername()) );
////        return "user";
////    }
//}
