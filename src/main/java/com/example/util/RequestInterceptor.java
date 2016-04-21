package com.example.util;

import com.example.api.ApiResponseStatus;
import com.example.api.ServiceException;
import com.example.entity.db.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by szymon on 21.04.16.
 */
@Component
public class RequestInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenUtil tokenUtil;

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("tokenUUID");
        if (token == null || !StringUtils.hasText(token)) {
            throw new ServiceException(ApiResponseStatus.TOKEN_NOT_FOUND);
        }
        User user = userRepository.findOneByToken_SessionToken(token);
        if (user == null) {
            throw new ServiceException(ApiResponseStatus.WRONG_TOKEN);
        }

        tokenUtil.checkSessionToken(token, user);
        return true;
    }
}
