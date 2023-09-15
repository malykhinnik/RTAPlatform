package com.rtaplatform.user_interaction;

import com.rtaplatform.redis.user_interaction.entity.UserInteractionEntity;

import java.time.LocalDateTime;
import java.util.List;

public class UserInteractionTestUtils {
    public static final String UUID = "685a5402-1d01-423b-89d4-e441350904f2";
    public static final LocalDateTime CREATED = LocalDateTime.of(2023, 9, 14, 13, 14, 12);
    public static final String CREATED_STR = CREATED.toString();
    public static final Long PRODUCT_ID_1 = 1L;
    public static final Long PRODUCT_ID_2 = 2L;
    public static final List<UserInteractionEntity> LIST_USER_INTERACTION_ENTITIES_BY_PRODUCT_ID_1 =
            List.of(
                    createUserInteractionEntityForTest(1L, PRODUCT_ID_1, 10),
                    createUserInteractionEntityForTest(2L, PRODUCT_ID_1, 10),
                    createUserInteractionEntityForTest(3L, PRODUCT_ID_1, 10),
                    createUserInteractionEntityForTest(4L, PRODUCT_ID_1, 10),
                    createUserInteractionEntityForTest(5L, PRODUCT_ID_1, 10),
                    createUserInteractionEntityForTest(6L, PRODUCT_ID_1, 10),
                    createUserInteractionEntityForTest(7L, PRODUCT_ID_1, 10),
                    createUserInteractionEntityForTest(8L, PRODUCT_ID_1, 10),
                    createUserInteractionEntityForTest(9L, PRODUCT_ID_1, 10),
                    createUserInteractionEntityForTest(10L, PRODUCT_ID_1, 10)
            );
    public static final Integer N_OF_USERS_BY_PRODUCT_ID_1 = LIST_USER_INTERACTION_ENTITIES_BY_PRODUCT_ID_1.size();
    public static final List<UserInteractionEntity> LIST_USER_INTERACTION_ENTITIES_BY_PRODUCT_ID_2 =
            List.of(
                    createUserInteractionEntityForTest(1L, PRODUCT_ID_2, 10),
                    createUserInteractionEntityForTest(2L, PRODUCT_ID_2, 10),
                    createUserInteractionEntityForTest(3L, PRODUCT_ID_2, 10)
            );
    public static final Integer N_OF_USERS_BY_PRODUCT_ID_2 = LIST_USER_INTERACTION_ENTITIES_BY_PRODUCT_ID_2.size();
    public static final Long USER_ID = 1L;
    public static final Integer TIME_SEC = 60;
    public static final Integer REDIS_PORT = 6379;

    public static UserInteractionEntity createUserInteractionEntityForTest(long userId, long productId, int timeSec) {
        return UserInteractionEntity.builder()
                .created(CREATED_STR)
                .uuid(java.util.UUID.randomUUID().toString())
                .userId(userId)
                .productId(productId)
                .timeSec(timeSec)
                .build();
    }
}
