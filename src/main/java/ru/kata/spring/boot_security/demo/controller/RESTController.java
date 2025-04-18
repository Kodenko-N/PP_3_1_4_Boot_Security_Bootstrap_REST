package ru.kata.spring.boot_security.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.RoleService;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api")
public class RESTController {

    private final UserService userService;
    private final RoleService roleService;


    public RESTController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    //Список пользователей
    @GetMapping("/admin/users")
    public ResponseEntity<AdminDataResponse> getAllUsers(@AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.getUserByName(userDetails.getUsername());
        List<User> users = userService.getAllUsers();
        Set<Role> roles =  roleService.getAllRoles();

        AdminDataResponse response = new AdminDataResponse(
                users,
                roles,
                currentUser
        );

        return ResponseEntity.ok(response);
    }

    //Создание нового пользователя
    @PostMapping("/users")
    public ResponseEntity<User> createUser(@RequestBody User userDTO) {
        User updatedUser = userService.update(userDTO);
        System.out.println("(REST) Creating user " + updatedUser);
        return ResponseEntity.ok(updatedUser);
    }

    //Обновление существующего пользователя
    @PutMapping("/users/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody User userDTO) {
            userDTO.setId(id);
            User updatedUser = userService.update(userDTO);
            return ResponseEntity.ok(updatedUser);
    }

    //Удаление пользователя
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.delete(id);
        System.out.println("(REST) Deleting user with id" + id);
        return ResponseEntity.noContent().build();
    }

    //Текущий пользователь
    @GetMapping("/user")
    public ResponseEntity<User> getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.getUserByName(userDetails.getUsername());
        return ResponseEntity.ok(user);
    }

    // DTO для ответа с данными админ-панели
    private static class AdminDataResponse {
        private final List<User> users;
        private final Set<Role> roles;
        private final User currentUser;

        public AdminDataResponse(List<User> users, Set<Role> roles, User currentUser) {
            this.users = users;
            this.roles = roles;
            this.currentUser = currentUser;
        }

        public List<User> getUsers() {
            return users;
        }

        public Set<Role> getRoles() {
            return roles;
        }

        public User getCurrentUser() {
            return currentUser;
        }

    }
}