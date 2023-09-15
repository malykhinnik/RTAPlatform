package com.rtaplatform.rest.app_user.dto;

import com.rtaplatform.app_user.model.AppUser;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class AppUserResponse {
    private final Long Id;
    private final String firstName;
    private final String lastName;

    public static AppUserResponse convertToDto(final AppUser au) {
        return AppUserResponse.builder()
                .Id(au.getId())
                .firstName(au.getFirstName())
                .lastName(au.getLastName())
                .build();
    }
}
