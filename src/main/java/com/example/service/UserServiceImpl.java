package com.example.service;

import com.example.api.ApiResponseStatus;
import com.example.api.ServiceException;
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
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public User getUser(HttpServletRequest httpServletRequest, Long id) {
        User user = this.userRepository.findOne(id);
        checkCorrelationBetweenUserIdAndIdSendedByClient(user);
        return this.userRepository.findOne(id);
    }


    @Override
    public void deleteUser(HttpServletRequest httpServletRequest, Long id) {
        User user = this.userRepository.findOne(id);
        checkCorrelationBetweenUserIdAndIdSendedByClient(user);
        this.userRepository.delete(user);
    }

    private void checkCorrelationBetweenUserIdAndIdSendedByClient(User user) {
        Long id = tokenUtil.getCoreUserIdFromToken(user.getToken().getSessionToken(), user.getToken().getSecret());
        if (!user.getId().equals(id)) {
            throw new ServiceException(ApiResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
