package com.example.util;

import com.example.api.ApiResponseStatus;
import com.example.api.ServiceException;
import com.example.entity.db.User;
import com.example.repository.UserRepository;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.impl.crypto.MacProvider;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.NumberUtils;

import java.security.Key;
import java.util.Date;

/**
 * Created by szymon on 21.04.16.
 */
@Component
public class TokenUtil {

    @Autowired
    UserRepository userRepository;

    public String createKey() {
        Key key = MacProvider.generateKey();
        return StringUtils.deleteWhitespace(Base64.encodeBase64String(key.getEncoded()));

    }

    public String createToken(String userSecret, Long userId) {
        int addMinuteTime = NumberUtils.convertNumberToTargetClass(5, Integer.class);
        Date targetTime = new Date();
        targetTime = DateUtils.addMinutes(targetTime, addMinuteTime);
        String token = Jwts.builder().setSubject(Long.toString(userId))
                .signWith(SignatureAlgorithm.HS512, userSecret).setExpiration(targetTime).compact();
        return token;
    }

    public void checkSessionToken(String token) {
        User user = userRepository.findOneByToken_SessionToken(token);
        if (user == null) {
            throw new ServiceException(ApiResponseStatus.AUTHENTICATION_FAUILURE);
        }
        try {
            Jwts.parser().setSigningKey(user.getToken().getSecret()).parseClaimsJws(token);
        } catch (SignatureException | ExpiredJwtException e1) {
            if (e1 instanceof ExpiredJwtException) {
                throw new ServiceException(ApiResponseStatus.TOKEN_EXPIRED);
            } else {
                throw new ServiceException(ApiResponseStatus.WRONG_TOKEN);
            }
        }
    }

    public Long getCoreUserIdFromToken(String token, String userSecret) {
        Long userCofId = Long.valueOf(
                Jwts.parser().setSigningKey(userSecret).parseClaimsJws(token).getBody().getSubject());
        return userCofId;

    }
}
