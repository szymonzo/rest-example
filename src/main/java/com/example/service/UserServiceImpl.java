package com.example.service;

import com.example.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by szymon on 21.04.16.
 */
@Service
public class UserServiceImpl implements UserService {

    @Override
    public List<User> getUsers() {
        return null;
    }

    @Override
    public User getUser() {
        return null;
    }

    @Override
    public void deleteUser() {

    }

    @Override
    public void updateUser() {

    }
}
