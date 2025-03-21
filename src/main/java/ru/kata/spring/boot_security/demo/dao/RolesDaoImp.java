package ru.kata.spring.boot_security.demo.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;
import ru.kata.spring.boot_security.demo.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Repository
public class RolesDaoImp {
    @PersistenceContext
    private EntityManager entityManager;

    public Set<Role> getAllRoles() {
        List<Role> roleList = entityManager.createQuery("from Role", Role.class).getResultList();
        return new HashSet<>(roleList);
    }

    public Role getRoleById(int id) {
        return entityManager.find(Role.class, id);
    }
}