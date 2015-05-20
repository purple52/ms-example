package com.balstar.example.ms.user.storage;

/**
 * Exception thrown when a user does not exist.
 */
public class UnknownUserException extends Exception {

    public UnknownUserException(String msg) {
        super(msg);
    }
}
