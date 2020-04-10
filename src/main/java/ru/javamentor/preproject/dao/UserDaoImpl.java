package ru.javamentor.preproject.dao;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.javamentor.preproject.model.User;

import javax.persistence.EntityManager;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    private EntityManager manager;

    @Autowired
    public UserDaoImpl(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public List<User> findAll() {
        Session currentSession = manager.unwrap(Session.class);
        Query<User> query = currentSession.createQuery("FROM User", User.class);
        return query.getResultList();
    }

    @Override
    public User findById(Long id) {
        Query query = manager.unwrap(Session.class).createQuery("FROM User WHERE id=:id");
        query.setParameter("id", id);
        return (User) query.uniqueResult();
    }

    @Override
    public void saveOrUpdate(User user) {
        manager.unwrap(Session.class).saveOrUpdate(user);
    }

    @Override
    public User findUserByName(String username) {
        Query query = manager.unwrap(Session.class).createQuery("FROM User WHERE username=:username");
        query.setParameter("username", username);
        return (User) query.uniqueResult();
    }

    @Override
    public void deleteById(Long id) {
        User user = findById(id);
        manager.unwrap(Session.class).delete(user);
    }
}
