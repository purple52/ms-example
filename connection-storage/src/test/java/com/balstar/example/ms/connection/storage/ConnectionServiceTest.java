package com.balstar.example.ms.connection.storage;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Unit tests for the ConnectionService class.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConnectionServiceTest {

    @InjectMocks
    private ConnectionService connectionService;

    @Test
    public void emptySetReturnedWhenNoConnections() {
        Set<Long> userIds = connectionService.getByUserId(1L);
        assertEquals(0, userIds.size());
    }

    @Test
    public void emptySetReturnedWhenNoConnectionsForGivenUser() {
        connectionService.addConnection(1L, 2L);

        Set<Long> userIds = connectionService.getByUserId(3L);

        assertEquals(0, userIds.size());
    }

    @Test
    public void oneUserReturnedWhenOneConnectionExists() {
        connectionService.addConnection(1L, 2L);

        Set<Long> userIds = connectionService.getByUserId(1L);

        assertEquals(1, userIds.size());
        assertTrue(userIds.contains(2L));
    }

    @Test
    public void twoUsersReturnedWhenTwoConnectionsExists() {
        connectionService.addConnection(1L, 2L);
        connectionService.addConnection(1L, 3L);

        Set<Long> userIds = connectionService.getByUserId(1L);

        assertEquals(2, userIds.size());
        assertTrue(userIds.contains(2L));
        assertTrue(userIds.contains(3L));
    }

    @Test
    public void unconnectedUsersAreNotReturned() {
        connectionService.addConnection(1L, 2L);
        connectionService.addConnection(1L, 3L);
        connectionService.addConnection(3L, 4L);
        connectionService.addConnection(3L, 5L);
        connectionService.addConnection(5L, 1L);

        Set<Long> userIds = connectionService.getByUserId(1L);

        assertEquals(3, userIds.size());
        assertTrue(userIds.contains(2L));
        assertTrue(userIds.contains(3L));
        assertTrue(userIds.contains(5L));
    }
}