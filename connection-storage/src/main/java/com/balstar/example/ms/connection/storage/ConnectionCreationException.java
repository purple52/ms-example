package com.balstar.example.ms.connection.storage;

/**
 * Exception for wrapping genertic issues creating connections
 */
public class ConnectionCreationException extends RuntimeException {

    public ConnectionCreationException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
