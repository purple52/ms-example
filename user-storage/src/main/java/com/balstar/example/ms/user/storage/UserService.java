package com.balstar.example.ms.user.storage;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Simple service for creating and storing users.
 * In a real life scenario, this service would abstract the underlying storage mechanism, but in this simple
 * example the storage is done within this class. Note that this needs to be thread safe.
 */
public class UserService {

    private Map<Long, User> users = new ConcurrentHashMap<>();

    private AtomicLong nextId = new AtomicLong(1);

    /**
     * Get a user by id.
     * @param id the id of the user to retrieve.
     * @return the user
     * @throws UnknownUserException if there is no user with the given id
     */
    public User getById(Long id) throws UnknownUserException {
        if (!users.containsKey(id)) {
            throw new UnknownUserException(String.format("Unknown user %s", id));
        }

        return users.get(id);
    }

    /**
     * Create a new user with the name provided.
     * @param name the name of the user
     * @return the newly created user
     */
    public User create(String name) {
        User newUser = new User(nextId.getAndIncrement(), name);
        users.put(newUser.getId(), newUser);
        return newUser;
    }
}
