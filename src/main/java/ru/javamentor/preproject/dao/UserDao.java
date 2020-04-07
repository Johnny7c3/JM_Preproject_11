package ru.javamentor.preproject.dao;

import ru.javamentor.preproject.model.User;

import java.util.List;

public interface UserDao {
    List<User> findAll();
    User findById(Long id);
    void saveOrUpdate(User user);
    User findUserByName(String name);
    void deleteById(Long id);
}
