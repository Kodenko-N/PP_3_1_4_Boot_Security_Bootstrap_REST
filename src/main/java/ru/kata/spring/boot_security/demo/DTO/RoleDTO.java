package ru.kata.spring.boot_security.demo.DTO;

import ru.kata.spring.boot_security.demo.model.User;
import java.util.HashSet;
import java.util.Set;


public class RoleDTO {

    private Long id;
    private String roleName;
    private String description;
    private Set<User> users = new HashSet<>();
    public RoleDTO() {
    }

    public RoleDTO(String role, String description) {
        this.roleName = role;
        this.description = description;
    }

    public RoleDTO(String role, String description, Set<User> users) {
        this.roleName = role;
        this.description = description;
        this.users = users;
    }

    public RoleDTO(Long id, String roleName, String description, Set<User> users) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
        this.users = users;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<User> getUsers() {
        return (Set<User>) users;
    }

    public void setUsers(Set<User> users) {
        this.users = (Set<User>) users;
    }
}
