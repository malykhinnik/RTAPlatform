package com.rtaplatform.analytics;

import com.rtaplatform.user_interaction.UserInteractionService;
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
public class AnalyticsServiceTest {
    @Mock
    private UserInteractionService userInteractionService;
    private AnalyticsService analyticsService;

    @BeforeEach
    public void before() {
        analyticsService = new AnalyticsService(userInteractionService);
    }

    @Test
    public void simpleMiddleUserEngagementCheck() {
        Mockito.when(userInteractionService.listUsersByProduct(PRODUCT_ID_1))
                .thenReturn(LIST_UNIQUE_USER_INTERACTION_BY_PRODUCT_ID_1);

        final Long middleUserEngagement = analyticsService.middleUserEngagement(PRODUCT_ID_1);
        assertEquals(10, middleUserEngagement);
    }

    @Test
    public void simpleNumberOfUniqueUsersCheck() {
        final List<UserInteraction> nonUniqueListOfUsers = LIST_UNIQUE_USER_INTERACTION_BY_PRODUCT_ID_1;
        nonUniqueListOfUsers.add(UserInteraction.builder().userId(USER_ID).productId(PRODUCT_ID_1).build());
        Mockito.when(userInteractionService.streamUsersByProduct(PRODUCT_ID_1))
                .thenReturn(nonUniqueListOfUsers.stream());

        final Long numberOfUniqueUsers = analyticsService.numberOfUniqueUsers(PRODUCT_ID_1);
        assertEquals((long) N_OF_UNIQUE_USERS_BY_PRODUCT_ID_1, numberOfUniqueUsers);
    }
}
