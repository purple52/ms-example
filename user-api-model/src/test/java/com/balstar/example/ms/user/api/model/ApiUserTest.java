package com.balstar.example.ms.user.api.model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for ApiUser class.
 */
public class ApiUserTest {

    @Test
    public void setAndGetId() {
        ApiUser user = new ApiUser();

        user.setId(99L);
        assertEquals(99L, user.getId().longValue());
    }

    @Test
    public void setAndGetName() {
        ApiUser user = new ApiUser();

        user.setName("testuser");
        assertEquals("testuser", user.getName());
    }
}