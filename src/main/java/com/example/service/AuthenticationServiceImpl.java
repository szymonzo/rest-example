package com.example.service;

import com.example.api.ApiResponseStatus;
import com.example.api.ServiceException;
import com.example.entity.api.AbstractAuthenticationIn;
import com.example.entity.api.AuthenticationOut;
import com.example.entity.api.StandardAuthenticationIn;
import com.example.entity.db.Token;
import com.example.entity.db.User;
import com.example.repository.UserRepository;
import com.example.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by szymon on 21.04.16.
 */
@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public AuthenticationOut authenticate(HttpServletRequest httpServletRequest, AbstractAuthenticationIn abstractAuthenticationIn) {
        if (abstractAuthenticationIn instanceof StandardAuthenticationIn) {
            return standardAuthentication((StandardAuthenticationIn) abstractAuthenticationIn);
        }
        throw new ServiceException(ApiResponseStatus.CONFIGURATION_NOT_SUPORTED);
    }

    private AuthenticationOut standardAuthentication(StandardAuthenticationIn standardAuthenticationIn) {
        AuthenticationOut authenticationOut = new AuthenticationOut();
        User user = userRepository.findOneByLoginAndPassword(standardAuthenticationIn.getLogin(), standardAuthenticationIn.getPassword());
        if (user == null)
            throw new ServiceException(ApiResponseStatus.AUTHENTICATION_FAUILURE);
        String secret = tokenUtil.createKey();
        String userToken = tokenUtil.createToken(secret, user.getId());
        manageToken(user, secret, userToken);
        authenticationOut.setToken(userToken);
        authenticationOut.setId(user.getId());
        return authenticationOut;
    }

    private void manageToken(User user, String secret, String userToken) {
        Token token = new Token();
        token.setSecret(secret);
        token.setSessionToken(userToken);
        user.setToken(token);
        userRepository.save(user);
    }


}
