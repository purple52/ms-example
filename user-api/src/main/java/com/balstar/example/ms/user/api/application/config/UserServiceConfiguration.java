package com.balstar.example.ms.user.api.application.config;

import com.balstar.example.ms.user.storage.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for user-related services.
 */
@Configuration
public class UserServiceConfiguration {

    @Bean
    public UserService userService() {
        return new UserService();
    }
}
