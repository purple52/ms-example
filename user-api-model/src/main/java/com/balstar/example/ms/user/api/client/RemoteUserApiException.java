package com.balstar.example.ms.user.api.client;

/**
 * Generic exception for wrapping problems with remote API calls.
 */
public class RemoteUserApiException extends Exception {

    public RemoteUserApiException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
