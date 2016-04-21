package com.example.service;

import com.example.entity.db.User;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by szymon on 21.04.16.
 */
public interface UserService {

    User getUser(HttpServletRequest httpServletRequest,Long id);

    void deleteUser(HttpServletRequest httpServletRequest,Long id);

}
