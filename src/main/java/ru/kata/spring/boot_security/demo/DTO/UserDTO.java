package ru.kata.spring.boot_security.demo.DTO;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import ru.kata.spring.boot_security.demo.model.Role;

public class UserDTO {

    private Long id;
    private String name;
    private String sureName;
    private Integer age = 0;
    private String email;
   // private String password;
    private Set<Role> roles = new HashSet<>();

    public UserDTO() {
    }

    public UserDTO(String name, String sureName, Integer age, String email, Set<Role> roles) {
        this.name = name;
        this.sureName = sureName;
        this.age = age;
        this.email = email;
        this.roles = roles;
    }

    public UserDTO(Long id, String name, String sureName, Integer age, String email, Set<Role> roles) {
        this.id = id;
        this.name = name;
        this.sureName = sureName;
        this.age = age;
        this.email = email;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSureName() {
        return sureName;
    }

    public void setSureName(String sureName) {
        this.sureName = sureName;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(id, userDTO.id) && Objects.equals(name, userDTO.name)
                && Objects.equals(sureName, userDTO.sureName) && Objects.equals(age, userDTO.age)
                && Objects.equals(email, userDTO.email)  && Objects.equals(roles, userDTO.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sureName, age, email, roles);
    }

    @Override
    public String toString() {
        return "UserDTO " +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sureName='" + sureName + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                ", roles=" + roles;
    }
}
