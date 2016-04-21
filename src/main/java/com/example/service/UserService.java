package com.example.service;

import com.example.entity.User;

import java.util.List;

/**
 * Created by szymon on 21.04.16.
 */
public interface UserService {

    List<User> getUsers();
    User getUser();
    void deleteUser();
    void updateUser();
}
