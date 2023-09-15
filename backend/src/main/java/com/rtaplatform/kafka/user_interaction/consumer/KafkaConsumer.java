package com.rtaplatform.kafka.user_interaction.consumer;

import com.rtaplatform.kafka.user_interaction.message.UserInteractionMessage;
import com.rtaplatform.user_interaction.UserInteractionService;
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
    private final UserInteractionService userInteractionService;
    @Getter
    private UserInteractionMessage userInteractionMessage;

    public KafkaConsumer(UserInteractionService userInteractionService) {
        this.userInteractionService = userInteractionService;
    }

    @KafkaListener(
            topics = "${spring.kafka.topic.name}",
            groupId = "${spring.kafka.consumer.group-id}",
            properties = {"spring.json.value.default.type=com.rtaplatform.kafka.user_interaction.message.UserInteractionMessage"})
    public void receive(ConsumerRecord<String, UserInteractionMessage> consumerRecord) {
        log.info("received message='{}'", consumerRecord.toString());
        userInteractionMessage = consumerRecord.value();
        latch.countDown();
        userInteractionService.addUserInteraction(userInteractionMessage.toUserInteraction());
    }
}
