package ru.javamentor.preproject.service;

import ru.javamentor.preproject.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
    User findById(Long id);
    void saveOrUpdate(User user);
    User findUserByName(String name);
    void deleteById(Long id);
}
