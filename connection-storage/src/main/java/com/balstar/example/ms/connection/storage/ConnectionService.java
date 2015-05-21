package com.balstar.example.ms.connection.storage;

import com.balstar.example.ms.user.api.client.RemoteUserApi;
import com.balstar.example.ms.user.api.client.RemoteUserApiException;
import org.jgrapht.UndirectedGraph;
import org.jgrapht.graph.DefaultEdge;
import org.jgrapht.graph.SimpleGraph;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Simple implementation of a service for managing connections between users.
 *
 * The service uses an undirected graph to store the connections. Internally, access to the graph is synchronised
 * to make it thread safe. This is all a bit quick and dirty.
 */
public class ConnectionService {

    private RemoteUserApi remoteUserApi;

    private final UndirectedGraph<Long, DefaultEdge> connections = new SimpleGraph<>(DefaultEdge.class);

    public ConnectionService(RemoteUserApi remoteUserApi) {
        this.remoteUserApi = remoteUserApi;
    }

    /**
     * Get the set of users that the given user is directly connected to.
     * @param id the id of the user to get the connections of
     * @return a set of user ids
     */
    public Set<Long> getByUserId(Long id) {
        synchronized (connections) {
            if (connections.containsVertex(id)) {
                Set<DefaultEdge> edges = connections.edgesOf(id);
                Set<Long> userIds = new HashSet<>(edges.size());
                for (DefaultEdge edge : edges) {
                    Long source = connections.getEdgeSource(edge);
                    Long target = connections.getEdgeTarget(edge);
                    if (!id.equals(source)) {
                        userIds.add(source);
                    }
                    if (!id.equals(target)) {
                        userIds.add(target);
                    }
                }
                return Collections.unmodifiableSet(userIds);
            } else {
                return Collections.emptySet();
            }

        }
    }

    /**
     * Add a new connection between two users.
     * @param userIdA the id of the user at one end of the connection
     * @param userIdB the id of the user at the other end of the connection
     */
    public void addConnection(Long userIdA, Long userIdB) {

        try {
            remoteUserApi.getByUserId(userIdA);
            remoteUserApi.getByUserId(userIdB);
        } catch (RemoteUserApiException ex) {
            throw new ConnectionCreationException("Could not verify that users both exist", ex);
        }

        synchronized (connections) {
            connections.addVertex(userIdA);
            connections.addVertex(userIdB);
            connections.addEdge(userIdA, userIdB);
        }
    }
}
