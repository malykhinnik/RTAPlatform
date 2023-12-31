package com.rtaplatform.app_user;

import com.rtaplatform.app_user.model.AppUser;
import org.junit.jupiter.api.Test;

import static com.rtaplatform.app_user.AppUserTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppUserTest {
    @Test
    public void updateAppUser() {
        final AppUser appUser = AppUser.builder()
                .id(USER_ID)
                .created(CREATED)
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .build();

        final AppUser updatedAppUser = appUser.update(
                AppUser.builder()
                        .firstName(NEW_USER_FIRST_NAME)
                        .lastName(USER_LAST_NAME)
                        .build());
        assertEquals(USER_ID, updatedAppUser.getId());
        assertEquals(CREATED, updatedAppUser.getCreated());
        assertEquals(NEW_USER_FIRST_NAME, updatedAppUser.getFirstName());
        assertEquals(USER_LAST_NAME, updatedAppUser.getLastName());
    }
}
