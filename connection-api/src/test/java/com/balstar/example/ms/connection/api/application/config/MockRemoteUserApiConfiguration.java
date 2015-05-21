package com.balstar.example.ms.connection.api.application.config;

import com.balstar.example.ms.user.api.client.RemoteUserApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import static org.mockito.Mockito.mock;

/**
 * Created by david on 21/05/15.
 */
public class MockRemoteUserApiConfiguration {

    @Bean
    @Primary
    public RemoteUserApi remoteUserApi() {
        return mock(RemoteUserApi.class);
    }
}
