package com.rtaplatform.redis.user_interaction;

import com.rtaplatform.redis.user_interaction.entity.UserInteractionEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.util.Optional;

import static com.rtaplatform.redis.user_interaction.UserInteractionTestConstants.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("redis-test")
public class UserInteractionEntityRepositoryTest {
    @Autowired
    UserInteractionEntityRepository userInteractionEntityRepository;

    @AfterEach
    void clean() {
        userInteractionEntityRepository.deleteAll();
    }

    @Test
    public void createNewUser() {
        final UserInteractionEntity userInteractionEntity = UserInteractionEntity.builder()
                .uuid(UUID)
                .userId(USER_ID)
                .productId(PRODUCT_ID)
                .timeSec(TIME_SEC)
                .build();

        final UserInteractionEntity newAppUserEntity = userInteractionEntityRepository.save(userInteractionEntity);
        assertNotNull(newAppUserEntity.getId());
        assertNotNull(newAppUserEntity.getCreated());
        assertEquals(UUID, newAppUserEntity.getUuid());
        assertEquals(USER_ID, newAppUserEntity.getUserId());
        assertEquals(PRODUCT_ID, newAppUserEntity.getProductId());
        assertEquals(TIME_SEC, newAppUserEntity.getTimeSec());
    }

    @Test
    public void readUser() {
        final UserInteractionEntity userInteractionEntity = userInteractionEntityRepository.save(
                UserInteractionEntity.builder()
                        .uuid(UUID)
                        .userId(USER_ID)
                        .productId(PRODUCT_ID)
                        .timeSec(TIME_SEC)
                        .build());

        final Optional<UserInteractionEntity> oFindedUserInteractionEntity = userInteractionEntityRepository.findById(userInteractionEntity.getId());
        assertTrue(oFindedUserInteractionEntity.isPresent());
        final UserInteractionEntity findedUserInteractionEntity = oFindedUserInteractionEntity.get();
        assertEquals(userInteractionEntity.getId(), findedUserInteractionEntity.getId());
        assertEquals(userInteractionEntity.getCreated(), findedUserInteractionEntity.getCreated());
        assertEquals(UUID, findedUserInteractionEntity.getUuid());
        assertEquals(USER_ID, findedUserInteractionEntity.getUserId());
        assertEquals(PRODUCT_ID, findedUserInteractionEntity.getProductId());
        assertEquals(TIME_SEC, findedUserInteractionEntity.getTimeSec());
    }
}
