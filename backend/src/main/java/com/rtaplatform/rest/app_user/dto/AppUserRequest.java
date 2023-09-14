package com.rtaplatform.rest.app_user.dto;

import com.rtaplatform.app_user.entity.AppUser;
import lombok.Builder;


@Builder
public class AppUserRequest {
    private final String firstName;
    private final String lastName;

    public AppUser convertToEntity() {
        return AppUser.builder()
                .firstName(firstName)
                .lastName(lastName)
                .build();
    }
}
