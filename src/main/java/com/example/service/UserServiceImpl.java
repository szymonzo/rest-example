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
        checkCorrelationBetweenUserIdAndIdSendedByClient(user,httpServletRequest);
        return this.userRepository.findOne(id);
    }


    @Override
    public void deleteUser(HttpServletRequest httpServletRequest, Long id) {
        User user = this.userRepository.findOne(id);
        checkCorrelationBetweenUserIdAndIdSendedByClient(user,httpServletRequest);
        this.userRepository.delete(user);
    }

    private void checkCorrelationBetweenUserIdAndIdSendedByClient(User user,HttpServletRequest httpServletRequest) {
       String token = httpServletRequest.getHeader("tokenUUID");
        User userDB = userRepository.findOneByToken_SessionToken(token);
        if (!user.getId().equals(userDB.getId())) {
            throw new ServiceException(ApiResponseStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
