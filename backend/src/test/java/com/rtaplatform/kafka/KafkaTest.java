package com.rtaplatform.kafka;

import com.rtaplatform.kafka.processing.KafkaConsumer;
import com.rtaplatform.kafka.produce.KafkaProducer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static com.rtaplatform.kafka.KafkaTestConstants.KAFKA_VALID_MESSAGE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@DirtiesContext
@EmbeddedKafka(partitions = 1, brokerProperties = {"listeners=PLAINTEXT://localhost:9092", "port=9092"})
public class KafkaTest {
    @Autowired
    public KafkaConsumer consumer;

    @Autowired
    public KafkaProducer producer;

    @Test
    public void whenSendingthenMessageReceived() throws InterruptedException {
        producer.sendMessage(KAFKA_VALID_MESSAGE);
        assertTrue(consumer.getLatch().await(10, TimeUnit.SECONDS));
        assertEquals(KAFKA_VALID_MESSAGE, consumer.getMessage());
    }
}
