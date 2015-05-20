package com.balstar.example.ms.user.api.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception thrown by the API if an unknown user is requested.
 */
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such user")
public class UserNotFoundException extends Exception {
}
