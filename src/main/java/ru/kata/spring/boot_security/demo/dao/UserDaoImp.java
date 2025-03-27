package ru.kata.spring.boot_security.demo.dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
        return entityManager.createQuery("from User u JOIN FETCH u.roles", User.class).getResultList();
    }

    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    public User getUserByName(String name) {
        try {
            return entityManager.createQuery("FROM User u LEFT JOIN FETCH u.roles WHERE u.name = :name", User.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(User user) {
        System.out.println("Saving user" + user.toString());
        entityManager.persist(user);
    }
    @Transactional
    public void update(User user) {
        System.out.println("Updating user" + user.toString());
        entityManager.merge(user);
    }

    public void delete(long id) {
        entityManager.createQuery("DELETE FROM User u WHERE u.id = :id")
                .setParameter("id", id)
                .executeUpdate();
    }
}