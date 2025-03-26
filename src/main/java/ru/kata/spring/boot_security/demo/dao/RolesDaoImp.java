package ru.kata.spring.boot_security.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class RolesDaoImp implements RoleDao {
    @PersistenceContext
    private EntityManager entityManager;

    public Set<Role> getAllRoles() {
        List<Role> roleList = entityManager.createQuery("from Role", Role.class).getResultList();
        return new HashSet<>(roleList);
    }

    public Role getRoleById(int id) {
        return entityManager.find(Role.class, id);
    }

    @Override
    public void save(Role role) {
        entityManager.persist(role);
    }

    @Override
    @Transactional
    public void update(Role role) {
        entityManager.merge(role);
    }

    @Override
    public void delete(int id) {
        entityManager.createQuery("DELETE FROM Role r WHERE r.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}