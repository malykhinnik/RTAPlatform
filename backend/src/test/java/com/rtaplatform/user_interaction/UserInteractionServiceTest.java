package com.rtaplatform.user_interaction;

import com.rtaplatform.redis.user_interaction.UserInteractionEntityRepository;
import com.rtaplatform.user_interaction.model.UserInteraction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static com.rtaplatform.user_interaction.UserInteractionTestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class UserInteractionServiceTest {
    @Mock
    private UserInteractionEntityRepository userInteractionEntityRepository;

    private UserInteractionService userInteractionService;

    @BeforeEach
    public void before() {
        userInteractionService = new UserInteractionService(userInteractionEntityRepository);
    }

    @Test
    public void whenCreateNonUniqueUserThenException() {
        Mockito.when(userInteractionEntityRepository.findAllByProductId(PRODUCT_ID_1))
                .thenReturn(LIST_USER_INTERACTION_ENTITIES_BY_PRODUCT_ID_1);

        List<UserInteraction> userInteractionsByProduct1 = userInteractionService.listUsersByProduct(PRODUCT_ID_1);
        assertEquals(N_OF_UNIQUE_USERS_BY_PRODUCT_ID_1, userInteractionsByProduct1.size());
        List<UserInteraction> userInteractionsByProduct2 = userInteractionService.listUsersByProduct(PRODUCT_ID_2);
        assertEquals(0, userInteractionsByProduct2.size());
    }
}
