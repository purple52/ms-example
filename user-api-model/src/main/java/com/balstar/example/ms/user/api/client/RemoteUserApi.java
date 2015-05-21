package com.balstar.example.ms.user.api.client;

import com.balstar.example.ms.user.api.model.ApiUser;
import com.balstar.example.ms.user.api.request.CreateUserRequest;

/**
 * Interface implemented by a remote client for the User API.
 */
public interface RemoteUserApi {

    /**
     * Get a user by id.
     * @param id the id of the user to retrieve
     * @return the user
     */
    ApiUser getByUserId(Long id) throws RemoteUserApiException;

    /**
     * Create a new user.
     * @param request details of the user to create
     * @return the created user
     */
    ApiUser createUser(CreateUserRequest request) throws RemoteUserApiException;
}
