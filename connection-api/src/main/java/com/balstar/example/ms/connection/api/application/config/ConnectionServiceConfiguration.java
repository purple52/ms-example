package com.balstar.example.ms.connection.api.application.config;

import com.balstar.example.ms.connection.storage.ConnectionService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration for connection-related services.
 */
@Configuration
public class ConnectionServiceConfiguration {

    @Bean
    public ConnectionService connectionService() {
        return new ConnectionService();
    }
}
