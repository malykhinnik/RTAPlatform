package com.rtaplatform.app_user;

import com.rtaplatform.app_user.exception.AppUserCreateException;
import com.rtaplatform.app_user.model.AppUser;
import com.rtaplatform.postgresql.app_user.AppUserEntityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.rtaplatform.app_user.AppUserTestConstants.USER_FIRST_NAME;
import static com.rtaplatform.app_user.AppUserTestConstants.USER_LAST_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AppUserServiceTest {
    @Mock
    private AppUserEntityRepository appUserEntityRepository;
    private AppUserService appUserService;

    @BeforeEach
    public void before() {
        appUserService = new AppUserService(appUserEntityRepository);
    }

    @Test
    public void whenCreateNonUniqueUserThenException() {
        Mockito.when(appUserEntityRepository.existsAppUserByFirstNameAndLastName(USER_FIRST_NAME, USER_LAST_NAME))
                .thenReturn(true);

        final AppUser appUser = AppUser.builder()
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .build();

        Exception exception = assertThrows(AppUserCreateException.class, () -> appUserService.create(appUser));
        assertEquals("User exist", exception.getMessage());
    }
}
