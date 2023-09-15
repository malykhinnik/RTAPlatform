package com.rtaplatform.redis.user_interaction;

import com.rtaplatform.redis.user_interaction.entity.UserInteractionEntity;
import com.rtaplatform.user_interaction.model.UserInteraction;
import org.junit.jupiter.api.Test;

import static com.rtaplatform.redis.user_interaction.UserInteractionTestConstants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserInteractionEntityTest {
    @Test
    public void convertAppUserRequestToAppUser() {
        final UserInteractionEntity userInteractionEntity = UserInteractionEntity.toEntity(
                UserInteraction.builder()
                        .uuid(UUID)
                        .userId(USER_ID)
                        .productId(PRODUCT_ID)
                        .timeSec(TIME_SEC)
                        .build());

        assertNotNull(userInteractionEntity.getCreated());
        assertEquals(UUID, userInteractionEntity.getUuid());
        assertEquals(USER_ID, userInteractionEntity.getUserId());
        assertEquals(PRODUCT_ID, userInteractionEntity.getProductId());
        assertEquals(TIME_SEC, userInteractionEntity.getTimeSec());
    }
}
