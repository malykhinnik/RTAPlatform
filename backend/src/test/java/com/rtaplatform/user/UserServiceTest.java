package com.rtaplatform.user;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    private static final String USER_FIRST_NAME = "TestUserFirstName";
    private static final String USER_LAST_NAME = "TestUserLastName";
    @Mock
    private UserRepository userRepository;
    private UserService userService;

    @BeforeEach
    public void before() {
        userService = new UserService(userRepository);
    }

    @Test
    public void whenCreateNonUniqueUserThenException() {
        Mockito.when(userRepository.existingUserByFirstNameAndLastName(USER_FIRST_NAME, USER_LAST_NAME))
                .thenReturn(true);

        User user = User.builder()
                .firstName(USER_FIRST_NAME)
                .lastName(USER_LAST_NAME)
                .build();

        Exception exception = assertThrows(UserCreateException.class, () -> userService.create(user));
        assertEquals("User exist", exception.getMessage());
    }
}
