package com.balstar.example.ms.connection.api.application.config;

import com.balstar.example.ms.connection.storage.ConnectionService;
import com.balstar.example.ms.user.api.client.RemoteUserApi;
import com.balstar.example.ms.user.api.client.RestTemplateRemoteUserApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Configuration for connection-related services.
 */
@Configuration
public class ConnectionServiceConfiguration {

    @Value("${userApiBaseUrl}")
    private String userApiBaseUrl;

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RemoteUserApi remoteUserApi() {
        return new RestTemplateRemoteUserApi(restTemplate(), userApiBaseUrl);
    }

    @Bean
    public ConnectionService connectionService() {
        return new ConnectionService(remoteUserApi());
    }
}
