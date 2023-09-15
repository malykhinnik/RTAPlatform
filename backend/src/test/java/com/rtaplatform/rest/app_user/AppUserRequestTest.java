package com.rtaplatform.rest.app_user;

import com.rtaplatform.app_user.model.AppUser;
import com.rtaplatform.rest.app_user.dto.AppUserRequest;
import org.junit.jupiter.api.Test;

import static com.rtaplatform.app_user.AppUserTestConstants.USER_FIRST_NAME;
import static com.rtaplatform.app_user.AppUserTestConstants.USER_LAST_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppUserRequestTest {
    @Test
    public void convertAppUserRequestToAppUser() {
        final AppUserRequest appUserRequest = AppUserRequest.builder()
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .build();

        final AppUser appUser = appUserRequest.convertToEntity();
        assertEquals(USER_FIRST_NAME, appUser.getFirstName());
        assertEquals(USER_LAST_NAME, appUser.getLastName());
    }
}
