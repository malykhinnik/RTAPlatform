package com.rtaplatform.data.processing;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.concurrent.CountDownLatch;

@Slf4j
@Component
@Getter
public class KafkaConsumer {
    private final CountDownLatch latch = new CountDownLatch(1);
    private String message;

    @KafkaListener(topics = "${spring.kafka.topic.name}")
    public void receive(ConsumerRecord<String, String> consumerRecord) {
        log.info("received message='{}'", consumerRecord.toString());
        message = consumerRecord.value();
        latch.countDown();
    }
}
