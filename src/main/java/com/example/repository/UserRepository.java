package com.example.repository;

import com.example.entity.db.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by szymon on 21.04.16.
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findOneByLoginAndPassword(String login, String password);

    User findOneByToken_SessionToken(String sessionToken);
}
