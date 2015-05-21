package com.balstar.example.ms.connection.api.application;

import com.balstar.example.ms.connection.api.application.config.MockRemoteUserApiConfiguration;
import com.balstar.example.ms.connection.api.request.CreateConnectionRequest;
import com.balstar.example.ms.user.api.client.RemoteUserApi;
import com.balstar.example.ms.user.api.model.ApiUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Integration tests for the whole Connection API application.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {
        Application.class,
        MockRemoteUserApiConfiguration.class
})
@WebIntegrationTest
public class ApplicationIntegrationTests {

    @Autowired
    private RemoteUserApi remoteUserApi;

    private RestTemplate restTemplate = new TestRestTemplate();

    @Before
    public void setUp() throws Exception {
        // Pretend all users exist
        when(remoteUserApi.getByUserId(anyLong())).thenReturn(new ApiUser());
    }

    @Test
    public void userWithNoConnectionsReturnsEmptyList() {
        ResponseEntity<Long[]> response = restTemplate.getForEntity("http://localhost:8080/users/5/connections", Long[].class, Collections.emptyMap());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(0, response.getBody().length);
    }

    @Test
    public void createConnectionReturnsOK() {
        CreateConnectionRequest createUserRequest = new CreateConnectionRequest();
        createUserRequest.setId(2L);

        ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:8080/users/4/connections", createUserRequest, Void.class, Collections.emptyMap());

        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void createdConnectionsCanBeRetrieved() {
        CreateConnectionRequest createUserRequestA = new CreateConnectionRequest();
        createUserRequestA.setId(2L);
        CreateConnectionRequest createUserRequestB = new CreateConnectionRequest();
        createUserRequestB.setId(3L);
        restTemplate.postForEntity("http://localhost:8080/users/1/connections", createUserRequestA, Void.class, Collections.emptyMap());
        restTemplate.postForEntity("http://localhost:8080/users/1/connections", createUserRequestB, Void.class, Collections.emptyMap());

        ResponseEntity<Long[]> response = restTemplate.getForEntity("http://localhost:8080/users/1/connections", Long[].class, Collections.emptyMap());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(2, response.getBody().length);
        assertTrue(Arrays.asList(response.getBody()).contains(2L));
        assertTrue(Arrays.asList(response.getBody()).contains(3L));
    }
}