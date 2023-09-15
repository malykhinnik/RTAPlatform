package com.rtaplatform.redis.user_interaction;

import com.rtaplatform.redis.user_interaction.entity.UserInteractionEntity;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import java.util.Optional;

import static com.rtaplatform.user_interaction.UserInteractionTestUtils.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Testcontainers
@ActiveProfiles("redis-test")
public class UserInteractionEntityRepositoryTest {
    @Container
    public static final GenericContainer<?> REDIS_CONTAINER = new GenericContainer<>(DockerImageName.parse("redis:7.2.1"))
                .withExposedPorts(REDIS_PORT);

    @Autowired
    public UserInteractionEntityRepository userInteractionEntityRepository;

    @DynamicPropertySource
    private static void registerRedisProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.redis.host", REDIS_CONTAINER::getHost);
        registry.add("spring.data.redis.port", () -> REDIS_CONTAINER.getMappedPort(REDIS_PORT).toString());
    }

    @AfterAll
    public static void afterAll() {
        REDIS_CONTAINER.stop();
    }

    @AfterEach
    void clean() {
        userInteractionEntityRepository.deleteAll();
    }

    @Test
    public void createNewUserInteraction() {
        final UserInteractionEntity userInteractionEntity = UserInteractionEntity.builder()
                .uuid(UUID)
                .userId(USER_ID)
                .productId(PRODUCT_ID_1)
                .timeSec(TIME_SEC)
                .build();

        final UserInteractionEntity newAppUserEntity = userInteractionEntityRepository.save(userInteractionEntity);
        assertNotNull(newAppUserEntity.getId());
        assertEquals(UUID, newAppUserEntity.getUuid());
        assertEquals(USER_ID, newAppUserEntity.getUserId());
        assertEquals(PRODUCT_ID_1, newAppUserEntity.getProductId());
        assertEquals(TIME_SEC, newAppUserEntity.getTimeSec());
    }

    @Test
    public void readUserInteraction() {
        final UserInteractionEntity userInteractionEntity = userInteractionEntityRepository.save(
                UserInteractionEntity.builder()
                        .uuid(UUID)
                        .userId(USER_ID)
                        .productId(PRODUCT_ID_1)
                        .timeSec(TIME_SEC)
                        .build());

        final Optional<UserInteractionEntity> oFindedUserInteractionEntity = userInteractionEntityRepository.findById(userInteractionEntity.getId());
        assertTrue(oFindedUserInteractionEntity.isPresent());
        final UserInteractionEntity findedUserInteractionEntity = oFindedUserInteractionEntity.get();
        assertEquals(userInteractionEntity.getId(), findedUserInteractionEntity.getId());
        assertEquals(userInteractionEntity.getCreated(), findedUserInteractionEntity.getCreated());
        assertEquals(UUID, findedUserInteractionEntity.getUuid());
        assertEquals(USER_ID, findedUserInteractionEntity.getUserId());
        assertEquals(PRODUCT_ID_1, findedUserInteractionEntity.getProductId());
        assertEquals(TIME_SEC, findedUserInteractionEntity.getTimeSec());
    }

    @Test
    public void findAllInteractionsByProduct() {
        LIST_USER_INTERACTION_ENTITIES_BY_PRODUCT_ID_1.forEach(uie -> userInteractionEntityRepository.save(uie));
        assertEquals(N_OF_USERS_BY_PRODUCT_ID_1, userInteractionEntityRepository.findAllByProductId(PRODUCT_ID_1).size());
        assertEquals(0, userInteractionEntityRepository.findAllByProductId(PRODUCT_ID_2).size());

        LIST_USER_INTERACTION_ENTITIES_BY_PRODUCT_ID_2.forEach(uie -> userInteractionEntityRepository.save(uie));
        assertEquals(N_OF_USERS_BY_PRODUCT_ID_2, userInteractionEntityRepository.findAllByProductId(PRODUCT_ID_2).size());
    }
}
