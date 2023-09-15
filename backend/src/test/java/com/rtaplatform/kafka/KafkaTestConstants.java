package com.rtaplatform.kafka;

import com.rtaplatform.kafka.user_interaction.message.UserInteractionMessage;

import java.time.LocalDateTime;

public class KafkaTestConstants {
    public static final String KAFKA_VALID_MESSAGE = "{\"created\":  1694697252,\"uuid\"  :    \"685a5402-1d01-423b-89d4-e441350904f2\"   ,\"user_id\": 1,\"product_id\": 1,\"time_sec\": 60}";
    public static final UserInteractionMessage KAFKA_VALID_USER_INTERACTION =
            UserInteractionMessage.builder()
                    .created(LocalDateTime.of(2023, 9, 14, 13, 14, 12))
                    .uuid("685a5402-1d01-423b-89d4-e441350904f2")
                    .userId(1L)
                    .productId(1L)
                    .timeSec(60)
                    .build();
}
