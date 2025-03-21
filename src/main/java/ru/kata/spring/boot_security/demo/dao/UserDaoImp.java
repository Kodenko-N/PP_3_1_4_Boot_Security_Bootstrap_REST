package ru.kata.spring.boot_security.demo.dao;


import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.User;
import java.util.List;


@Repository
public class UserDaoImp implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getAllUsers() {
       System.out.println("searching user name 'admin'. Found " + getUserByName("admin").toString()); //!!!!!!!!!!!!!!
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }

    public User getUserByName(String name) {
        try {
            Query query = entityManager.createQuery("FROM User u WHERE u.name = :name", User.class);
            query.setParameter("name", name);
            return (User) query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public void save(User user) {
        System.out.println("Saving user" + user.toString());
        entityManager.persist(user);
    }

    @Transactional
    @org.springframework.transaction.annotation.Transactional
    public void update(User user) {
        System.out.println("Updating user" + user.toString());
        entityManager.merge(user);
    }

    public void delete(int id) {
        entityManager.remove(entityManager.find(User.class, id));
    }
}