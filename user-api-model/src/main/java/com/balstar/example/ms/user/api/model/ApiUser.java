package com.balstar.example.ms.user.api.model;

/**
 * Represents a user in the model used by the user API.
 * This is a simple POJO for serialization and deserialization purposes.
 */
public class ApiUser {

    private Long id;
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
