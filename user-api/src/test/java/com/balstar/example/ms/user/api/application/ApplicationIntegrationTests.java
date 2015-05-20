package com.balstar.example.ms.user.api.application;


import com.balstar.example.ms.user.api.model.ApiUser;
import com.balstar.example.ms.user.api.request.CreateUserRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

import static org.junit.Assert.assertEquals;

/**
 * Integration test for the whole User API application.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest
public class ApplicationIntegrationTests {

    private RestTemplate restTemplate = new TestRestTemplate();

    @Test
    public void emptyAPIReturns404() {
        ResponseEntity<ApiUser> response = restTemplate.getForEntity("http://localhost:8080/user/1", ApiUser.class, Collections.emptyMap());

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void createUserReturnsApiUser() {
        CreateUserRequest createUserRequest = new CreateUserRequest();
        createUserRequest.setName("test user name");

        ResponseEntity<ApiUser> response = restTemplate.postForEntity("http://localhost:8080/user", createUserRequest, ApiUser.class, Collections.emptyMap());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(1L, response.getBody().getId().longValue());
        assertEquals("test user name", response.getBody().getName());
    }
}