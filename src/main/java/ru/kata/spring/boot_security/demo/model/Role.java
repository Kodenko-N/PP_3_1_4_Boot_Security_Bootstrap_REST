package ru.kata.spring.boot_security.demo.model;

import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "Roles")
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "roleName", unique = true, nullable = false)
    private String roleName;

    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<>();
    public Role() {
    }

    public Role(String role, String description) {
        this.roleName = role;
        this.description = description;
    }

    public Role(String role, String description, HashSet<User> users) {
        this.roleName = role;
        this.description = description;
        this.users = users;
    }

    public Role(Long id, String roleName, String description, HashSet<User> users) {
        this.id = id;
        this.roleName = roleName;
        this.description = description;
        this.users = users;
    }

    //Конструктор для GrantedAuthority
    public Role(String name) {
        this.roleName = name.startsWith("ROLE_") ? name : "ROLE_" + name;
    }

    // Реализация метода из GrantedAuthority
    @Override
    public String getAuthority() {
        return roleName;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    //Фактически дублирует getAuthority()
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

    public HashSet<User> getUsers() {
        return (HashSet<User>) users;
    }

    public void setUsers(HashSet<User> users) {
        this.users = (Set<User>) users;
    }

    @Override
    public String toString() {
        return "Roles is " + roleName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(roleName, role.roleName);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(roleName);
    }
}
