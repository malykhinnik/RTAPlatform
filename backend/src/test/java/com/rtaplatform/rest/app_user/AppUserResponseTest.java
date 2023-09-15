package com.rtaplatform.rest.app_user;

import com.rtaplatform.app_user.model.AppUser;
import com.rtaplatform.rest.app_user.dto.AppUserResponse;
import org.junit.jupiter.api.Test;

import static com.rtaplatform.app_user.AppUserTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppUserResponseTest {
    @Test
    public void convertAppUserToAppUserResponse() {
        final AppUser appUser = AppUser.builder()
                .id(USER_ID)
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .build();

        final AppUserResponse appUserResponse = AppUserResponse.convertToDto(appUser);
        assertEquals(USER_ID, appUserResponse.getId());
        assertEquals(USER_FIRST_NAME, appUserResponse.getFirstName());
        assertEquals(USER_LAST_NAME, appUserResponse.getLastName());
    }
}
