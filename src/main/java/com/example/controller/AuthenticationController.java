package com.example.controller;

import com.example.api.ApiBaseResponse;
import com.example.api.ApiResponseBuilder;
import com.example.entity.api.AbstractAuthenticationIn;
import com.example.entity.api.AuthenticationOut;
import com.example.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by szymon on 21.04.16.
 */
@RestController
@RequestMapping("/authentications")
public class AuthenticationController {

    @Autowired
    private ApiResponseBuilder apiResponseBuilder;

    @Autowired
    private AuthenticationService authenticationService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<ApiBaseResponse<AuthenticationOut>> authenticate(HttpServletRequest httpServletRequest, @RequestBody @Valid AbstractAuthenticationIn abstractAuthenticationIn) {
        AuthenticationOut authenticationOut = authenticationService.authenticate(httpServletRequest,abstractAuthenticationIn);
        return new ResponseEntity<>(apiResponseBuilder.success(authenticationOut), HttpStatus.OK);
    }
}
