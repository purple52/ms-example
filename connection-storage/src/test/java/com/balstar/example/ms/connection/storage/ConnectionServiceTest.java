package com.balstar.example.ms.connection.storage;

import com.balstar.example.ms.user.api.client.RemoteUserApi;
import com.balstar.example.ms.user.api.client.RemoteUserApiException;
import com.balstar.example.ms.user.api.model.ApiUser;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;

/**
 * Unit tests for the ConnectionService class.
 */
@RunWith(MockitoJUnitRunner.class)
public class ConnectionServiceTest {

    @Mock
    private RemoteUserApi remoteUserApi;

    @InjectMocks
    private ConnectionService connectionService;

    @Before
    public void setUp() throws Exception {
        // Default to assuming all users exist
        when(remoteUserApi.getByUserId(anyLong())).thenReturn(new ApiUser());
    }

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

    @Test(expected = ConnectionCreationException.class)
    public void unknownUserThrowsException() throws Exception {
        when(remoteUserApi.getByUserId(99L)).thenThrow(new RemoteUserApiException("User does not exist", new RuntimeException()));

        connectionService.addConnection(1L, 99L);
    }
}