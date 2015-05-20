package com.balstar.example.ms.user.storage;

/**
 * Represents a user in the model used by the user service.
 */
public class User {

    private Long id;
    private String name;

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
