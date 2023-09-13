package com.rtaplatform.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.rtaplatform.user.Constants.USER_FIRST_NAME;
import static com.rtaplatform.user.Constants.USER_LAST_NAME;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class AppAppUserServiceTest {
    @Mock
    private AppUserRepository appUserRepository;
    private AppUserService appUserService;

    @BeforeEach
    public void before() {
        appUserService = new AppUserService(appUserRepository);
    }

    @Test
    public void whenCreateNonUniqueUserThenException() {
        Mockito.when(appUserRepository.existsAppUserByFirstNameAndLastName(USER_FIRST_NAME, USER_LAST_NAME))
                .thenReturn(true);

        final AppUser appUser = AppUser.builder()
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .build();

        Exception exception = assertThrows(AppUserCreateException.class, () -> appUserService.create(appUser));
        assertEquals("User exist", exception.getMessage());
    }
}
