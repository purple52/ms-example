package com.balstar.example.ms.user.storage;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the User class.
 */
public class UserTest {

    @Test
    public void getIdReturnsCorrectId() throws Exception {
        User user = new User(999L, "whatever");
        assertEquals(999L, user.getId().longValue());
    }

    @Test
    public void getNameReturnsCorrectId() throws Exception {
        User user = new User(999L, "whatever");
        assertEquals("whatever", user.getName());
    }
}