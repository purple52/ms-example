package com.balstar.example.ms.user.storage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;

/**
 * Unit tests for the UserService class.
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @InjectMocks
    private UserService userService;

    @Test(expected = UnknownUserException.class)
    public void emptyStorageReturnsUnknownUser() throws Exception {
        userService.getById(1L);
    }

    @Test
    public void createFirstUserGivesCorrectId() throws Exception {
        User user = userService.create("testuser");
        assertEquals(1L, user.getId().longValue());
    }

    @Test
    public void createFirstUserGivesCorrectName() throws Exception {
        User user = userService.create("testuser");
        assertEquals("testuser", user.getName());
    }

    @Test
    public void firstUserIsStored() throws Exception {
        userService.create("testuser");

        User user = userService.getById(1L);

        assertEquals(1L, user.getId().longValue());
        assertEquals("testuser", user.getName());
    }

    @Test
    public void secondUserIsStored() throws Exception {
        userService.create("testuser1");
        userService.create("testuser2");

        User user = userService.getById(2L);

        assertEquals(2L, user.getId().longValue());
        assertEquals("testuser2", user.getName());
    }
}