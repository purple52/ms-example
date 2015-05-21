package com.balstar.example.ms.user.api.client;

import com.balstar.example.ms.user.api.model.ApiUser;
import com.balstar.example.ms.user.api.request.CreateUserRequest;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

/**
 * Very basic client implementation for connecting to a remote User API using a Spring RestTemplate.
 *
 * Minimal error handling.
 */
public class RestTemplateRemoteUserApi implements RemoteUserApi {

    private RestTemplate restTemplate;

    private String baseUrl;

    public RestTemplateRemoteUserApi(RestTemplate restTemplate, String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    @Override
    public ApiUser getByUserId(Long id) throws RemoteUserApiException {
        try {
            return restTemplate.getForObject(String.format("%s/users/%s", baseUrl, id), ApiUser.class, Collections.emptyMap());
        } catch (RestClientException ex) {
            throw new RemoteUserApiException("Could not retrieve user", ex);
        }
    }

    @Override
    public ApiUser createUser(CreateUserRequest request) throws RemoteUserApiException {
        try {
            return restTemplate.postForObject(String.format("%s/users", baseUrl), request, ApiUser.class, Collections.emptyMap());
        } catch (RestClientException ex) {
            throw new RemoteUserApiException("Could not create user", ex);
        }
    }
}
