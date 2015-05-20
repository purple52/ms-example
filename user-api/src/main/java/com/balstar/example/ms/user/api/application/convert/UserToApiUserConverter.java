package com.balstar.example.ms.user.api.application.convert;

import com.balstar.example.ms.user.api.model.ApiUser;
import com.balstar.example.ms.user.storage.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Converter for changing the internal user representation into an API object.
 */
@Component
public class UserToApiUserConverter implements Converter<User, ApiUser> {

    @Override
    public ApiUser convert(User user) {
        ApiUser apiUser = new ApiUser();
        apiUser.setId(user.getId());
        apiUser.setName(user.getName());
        return apiUser;
    }
}
