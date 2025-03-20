package ru.kata.spring.boot_security.demo.model;

import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "surname")
    private String sureName;

    public User() {
    }

    public User(String name, String sureName) {
        this.name = name;
        this.sureName = sureName;
    }

    public User(Long id, String name, String sureName) {
        this.id = id;
        this.name = name;
        this.sureName = sureName;
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

    @Override
    public String toString() {
        return "ID = " + id + " User name is " + name + ", FamilyName is " + sureName;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(name, user.name) && Objects.equals(sureName, user.sureName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, sureName);
    }
}
