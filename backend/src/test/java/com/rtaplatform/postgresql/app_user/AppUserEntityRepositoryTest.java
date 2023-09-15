package com.rtaplatform.postgresql.app_user;

import com.rtaplatform.postgresql.app_user.entity.AppUserEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static com.rtaplatform.app_user.AppUserTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("postgresql-test")
public class AppUserEntityRepositoryTest {
    @Autowired
    AppUserEntityRepository appUserEntityRepository;

    @AfterEach
    void clean() {
        appUserEntityRepository.deleteAll();
    }

    @Test
    public void createNewUser() {
        final AppUserEntity appUserEntity = AppUserEntity.builder()
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .build();

        final AppUserEntity newAppUserEntity = appUserEntityRepository.save(appUserEntity);
        assertNotNull(newAppUserEntity.getId());
        assertNotNull(newAppUserEntity.getCreated());
        assertNotNull(newAppUserEntity.getUpdated());
        assertEquals(USER_FIRST_NAME, newAppUserEntity.getFirstName());
        assertEquals(USER_LAST_NAME, newAppUserEntity.getLastName());
    }

    @Test
    public void readUser() {
        final AppUserEntity appUserEntity = appUserEntityRepository.save(
                AppUserEntity.builder()
                        .firstName(USER_FIRST_NAME)
                        .lastName(USER_LAST_NAME)
                        .build());

        final Optional<AppUserEntity> oFindedUser = appUserEntityRepository.findById(appUserEntity.getId());
        assertTrue(oFindedUser.isPresent());
        final AppUserEntity findedUser = oFindedUser.get();
        assertEquals(appUserEntity.getId(), findedUser.getId());
        assertEquals(appUserEntity.getCreated(), findedUser.getCreated());
        assertEquals(appUserEntity.getUpdated(), findedUser.getUpdated());
        assertEquals(USER_FIRST_NAME, findedUser.getFirstName());
        assertEquals(USER_LAST_NAME, findedUser.getLastName());
    }

    @Test
    public void updateUser() {
        final AppUserEntity appUserEntity = appUserEntityRepository.save(
                AppUserEntity.builder()
                        .firstName(USER_FIRST_NAME)
                        .lastName(USER_LAST_NAME)
                        .build());
        final AppUserEntity updatedAppUser = appUserEntityRepository.save(
                AppUserEntity.builder()
                        .id(appUserEntity.getId())
                        .created(appUserEntity.getCreated())
                        .updated(appUserEntity.getUpdated())
                        .firstName(NEW_USER_FIRST_NAME)
                        .lastName(appUserEntity.getLastName())
                        .build());
        assertEquals(appUserEntity.getId(), updatedAppUser.getId());
        assertEquals(appUserEntity.getCreated(), updatedAppUser.getCreated());
        assertTrue(appUserEntity.getUpdated().isBefore(updatedAppUser.getUpdated()));
        assertEquals(NEW_USER_FIRST_NAME, updatedAppUser.getFirstName());
        assertEquals(USER_LAST_NAME, updatedAppUser.getLastName());
    }

    @Test
    public void deleteUser() {
        final AppUserEntity appUserEntity = appUserEntityRepository.save(
                AppUserEntity.builder()
                        .firstName(USER_FIRST_NAME)
                        .lastName(USER_LAST_NAME)
                        .build());
        appUserEntityRepository.deleteById(appUserEntity.getId());
        final Optional<AppUserEntity> oFindedUser = appUserEntityRepository.findById(appUserEntity.getId());
        assertTrue(oFindedUser.isEmpty());
    }
}
