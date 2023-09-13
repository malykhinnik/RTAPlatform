package com.rtaplatform.user;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static com.rtaplatform.user.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("postgresql-test")
public class AppAppUserRepositoryTest {
    @Autowired
    AppUserRepository appUserRepository;

    @AfterEach
    void clean() {
        appUserRepository.deleteAll();
    }

    @Test
    public void createNewUser() {
        final AppUser appUser = AppUser.builder()
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .build();

        final AppUser newAppUser = appUserRepository.save(appUser);
        assertNotNull(newAppUser.getId());
        assertNotNull(newAppUser.getCreated());
        assertNotNull(newAppUser.getUpdated());
        assertEquals(USER_FIRST_NAME, newAppUser.getFirstName());
        assertEquals(USER_LAST_NAME, newAppUser.getLastName());
    }

    @Test
    public void readUser() {
        final AppUser appUser = appUserRepository.save(AppUser.builder()
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .build());

        final Optional<AppUser> oFindedUser = appUserRepository.findById(appUser.getId());
        assertTrue(oFindedUser.isPresent());
        final AppUser findedUser = oFindedUser.get();
        assertEquals(appUser.getId(), findedUser.getId());
        assertEquals(appUser.getCreated(), findedUser.getCreated());
        assertEquals(appUser.getUpdated(), findedUser.getUpdated());
        assertEquals(USER_FIRST_NAME, findedUser.getFirstName());
        assertEquals(USER_LAST_NAME, findedUser.getLastName());
    }

    @Test
    public void updateUser() {
        final AppUser appUser = appUserRepository.save(AppUser.builder()
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .build());
        appUser.updateFirstName(NEW_USER_FIRST_NAME);
        final AppUser updatedAppUser = appUserRepository.save(appUser);
        assertEquals(appUser.getId(), updatedAppUser.getId());
        assertEquals(appUser.getCreated(), updatedAppUser.getCreated());
        assertTrue(appUser.getUpdated().isBefore(updatedAppUser.getUpdated()));
        assertEquals(NEW_USER_FIRST_NAME, updatedAppUser.getFirstName());
        assertEquals(USER_LAST_NAME, updatedAppUser.getLastName());
    }

    @Test
    public void deleteUser() {
        final AppUser appUser = appUserRepository.save(AppUser.builder()
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .build());
        appUserRepository.deleteById(appUser.getId());
        final Optional<AppUser> oFindedUser = appUserRepository.findById(appUser.getId());
        assertTrue(oFindedUser.isEmpty());
    }
}
