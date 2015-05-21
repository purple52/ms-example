package com.balstar.example.ms.connection.api.application.controller;

import com.balstar.example.ms.connection.api.request.CreateConnectionRequest;
import com.balstar.example.ms.connection.storage.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

/**
 * Controller for connection-related API calls.
 */
@RestController
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    @RequestMapping("/users/{id}/connections")
    public Set<Long> getConnectionsByUserId(@PathVariable Long id) {
        return connectionService.getByUserId(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users/{id}/connections")
    public void createConnection(@PathVariable Long id, @RequestBody CreateConnectionRequest createConnectionRequest) {
        connectionService.addConnection(id, createConnectionRequest.getId());
    }
}
