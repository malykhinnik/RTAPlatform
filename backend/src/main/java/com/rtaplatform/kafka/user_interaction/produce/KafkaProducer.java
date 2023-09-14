package com.rtaplatform.kafka.user_interaction.produce;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import static com.rtaplatform.kafka.KafkaUtils.isValidMessage;

@Slf4j
@Component
public class KafkaProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    @Value(value = "${spring.kafka.topic.name}")
    private String kafkaTopicName;

    @Autowired
    public KafkaProducer(final KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(final String message) {
        if (!isValidMessage(message)) {
            log.info("Not valid message='{}' to topic='{}", message, kafkaTopicName);
            return;
        }

        log.info("sending message='{}' to topic='{}'", message, kafkaTopicName);
        kafkaTemplate.send(kafkaTopicName, message);
    }
}
