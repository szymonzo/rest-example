package com.example.util;

import com.example.entity.db.User;
import com.example.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 * Created by szymon on 21.04.16.
 */
@Component
public class DataBaseInitializer implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        initialize();
    }

    private void initialize() {
        if (!userRepository.findAll().isEmpty()) {
            return;
        }
        initializeUsers();

    }

    private void initializeUsers() {
        User adam = new User();
        adam.setLogin("jestemAdam");
        adam.setName("Adam");
        adam.setPassword("adam123");
        userRepository.save(adam);

        User kamil = new User();
        kamil.setName("kamil");
        kamil.setPassword("kamil123");
        kamil.setLogin("jestemKamil");
        userRepository.save(kamil);
    }
}
