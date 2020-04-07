package ru.javamentor.preproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.javamentor.preproject.dao.UserDao;
import ru.javamentor.preproject.model.User;

@Service
@Transactional
public class UserDetailServiceImpl implements UserDetailsService {
    private UserDao userDao;

    @Autowired
    public UserDetailServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = null;
        try {
            user = userDao.findUserByName(username);
        } catch (UsernameNotFoundException ex) {
            ex.printStackTrace();
        }
        return user;
    }
}
