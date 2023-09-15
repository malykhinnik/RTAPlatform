package com.rtaplatform.redis.user_interaction;

import com.rtaplatform.redis.user_interaction.entity.UserInteractionEntity;
import com.rtaplatform.user_interaction.model.UserInteraction;
import org.junit.jupiter.api.Test;

import static com.rtaplatform.user_interaction.UserInteractionTestUtils.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserInteractionEntityTest {
    @Test
    public void createUserInteractionEntityFromUserInteraction() {
        final UserInteractionEntity userInteractionEntity = UserInteractionEntity.toEntity(
                UserInteraction.builder()
                        .uuid(UUID)
                        .userId(USER_ID)
                        .productId(PRODUCT_ID_1)
                        .timeSec(TIME_SEC)
                        .build());

        assertNotNull(userInteractionEntity.getCreated());
        assertEquals(UUID, userInteractionEntity.getUuid());
        assertEquals(USER_ID, userInteractionEntity.getUserId());
        assertEquals(PRODUCT_ID_1, userInteractionEntity.getProductId());
        assertEquals(TIME_SEC, userInteractionEntity.getTimeSec());
    }

    @Test
    public void convertUserInteractionEntityToModel() {
        final UserInteraction userInteraction = UserInteractionEntity.builder()
                .created(CREATED_STR)
                .uuid(UUID)
                .userId(USER_ID)
                .productId(PRODUCT_ID_1)
                .timeSec(TIME_SEC)
                .build()
                .toModel();

        assertEquals(CREATED, userInteraction.getCreated());
        assertEquals(UUID, userInteraction.getUuid());
        assertEquals(USER_ID, userInteraction.getUserId());
        assertEquals(PRODUCT_ID_1, userInteraction.getProductId());
        assertEquals(TIME_SEC, userInteraction.getTimeSec());
    }
}
