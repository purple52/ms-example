package com.balstar.example.ms.user.api.request;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the CreateUserRequest class.
 */
public class CreateUserRequestTest {

    @Test
    public void setAndGetName() {
        CreateUserRequest createUserRequest = new CreateUserRequest();

        createUserRequest.setName("testuser");
        assertEquals("testuser", createUserRequest.getName());
    }
}