package com.balstar.example.ms.user.api.application.controller;

import com.balstar.example.ms.user.api.application.exception.UserNotFoundException;
import com.balstar.example.ms.user.api.model.ApiUser;
import com.balstar.example.ms.user.api.request.CreateUserRequest;
import com.balstar.example.ms.user.storage.UnknownUserException;
import com.balstar.example.ms.user.storage.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.web.bind.annotation.*;

/**
 * Controller for user-related API calls.
 */
@RestController
public class UserController {

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private UserService userService;

    @RequestMapping("/users/{id}")
    public ApiUser getUser(@PathVariable Long id) throws UserNotFoundException {
        try {
            return conversionService.convert(userService.getById(id), ApiUser.class);
        } catch (UnknownUserException ex) {
            throw new UserNotFoundException();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users")
    public ApiUser createUser(@RequestBody CreateUserRequest createUserRequest) {
        return conversionService.convert(userService.create(createUserRequest.getName()), ApiUser.class);
    }
}
