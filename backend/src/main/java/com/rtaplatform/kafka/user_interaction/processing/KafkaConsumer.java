package com.rtaplatform.kafka.user_interaction.processing;

import com.rtaplatform.kafka.user_interaction.dto.UserInteractionMessage;
import com.rtaplatform.redis.user_interaction.UserInteractionRedisService;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
public class KafkaConsumer {
    @Getter
    private final CountDownLatch latch = new CountDownLatch(1);
    private final UserInteractionRedisService userInteractionRedisService;
    @Getter
    private UserInteractionMessage userInteractionMessage;

    public KafkaConsumer(UserInteractionRedisService userInteractionRedisService) {
        this.userInteractionRedisService = userInteractionRedisService;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            properties = {"spring.json.value.default.type=com.rtaplatform.kafka.user_interaction.dto.UserInteractionMessage"})
    public void receive(ConsumerRecord<String, UserInteractionMessage> consumerRecord) {
        log.info("received message='{}'", consumerRecord.toString());
        userInteractionMessage = consumerRecord.value();
        latch.countDown();
        userInteractionRedisService.addUserInteraction(userInteractionMessage.toUserInteraction());
    }
}
