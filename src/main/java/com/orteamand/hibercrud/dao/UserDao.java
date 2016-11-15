package com.orteamand.hibercrud.dao;


import com.orteamand.hibercrud.model.User;

import java.util.List;

public interface UserDao {
    void addUser(User user);

    void updateUser(User user);

    void removeUser(int id);

    User getUserById(int id);

    List<User> listUsers(Long page);

    List<User> listUsers(String name);

    List<User> listUsers(int age);
}
