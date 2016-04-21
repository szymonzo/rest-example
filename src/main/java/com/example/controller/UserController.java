package com.example.controller;

import com.example.api.ApiBaseResponse;
import com.example.api.ApiResponseBuilder;
import com.example.entity.db.User;
import com.example.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by szymon on 21.04.16.
 */

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private ApiResponseBuilder apiResponseBuilder;

    @Autowired
    private UserServiceImpl userService;


    @RequestMapping(value ="/{id}" ,method = RequestMethod.GET)
    public ResponseEntity<ApiBaseResponse<User>> users(HttpServletRequest httpServletRequest, @PathVariable("id") Long id){
        User user = userService.getUser(httpServletRequest,id);
        return new ResponseEntity<>(apiResponseBuilder.success(user), HttpStatus.OK);
    }

    @RequestMapping(value ="/{id}" ,method = RequestMethod.DELETE)
    public ResponseEntity<ApiBaseResponse<Void>> user(HttpServletRequest httpServletRequest,@PathVariable("id") Long id){
        userService.deleteUser(httpServletRequest,id);
        return new ResponseEntity<>(apiResponseBuilder.success(), HttpStatus.OK);
    }


}
