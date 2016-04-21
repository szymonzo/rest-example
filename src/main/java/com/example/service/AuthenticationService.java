package com.example.service;

import com.example.entity.api.AbstractAuthenticationIn;
import com.example.entity.api.AuthenticationOut;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by szymon on 21.04.16.
 */
public interface AuthenticationService {

    AuthenticationOut authenticate(HttpServletRequest httpServletRequest,AbstractAuthenticationIn abstractAuthenticationIn);
}
