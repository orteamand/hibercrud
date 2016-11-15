package com.orteamand.hibercrud.service;

import com.orteamand.hibercrud.dao.UserDao;
import com.orteamand.hibercrud.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    @Transactional
    public void addUser(User user) {
        userDao.addUser(user);
    }

    @Override
    @Transactional
    public void updateUser(User user) {
        userDao.updateUser(user);
    }

    @Override
    @Transactional
    public void removeUser(int id) {
        userDao.removeUser(id);
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public List<User> listUsers(Long page) {
        return userDao.listUsers(page);
    }

    @Override
    @Transactional
    public List<User> listUsers(String name) {
        return userDao.listUsers(name);
    }

    @Override
    @Transactional
    public List<User> listUsers(int age) {
        return userDao.listUsers(age);
    }
}
