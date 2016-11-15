package com.orteamand.hibercrud.service;


import com.orteamand.hibercrud.model.User;

import java.util.List;

public interface UserService {
    void addUser(User user);

    void updateUser(User user);

    void removeUser(int id);

    User getUserById(int id);

    List<User> listUsers(Long page);

    List<User> listUsers(String name);

    List<User> listUsers(int age);
}
