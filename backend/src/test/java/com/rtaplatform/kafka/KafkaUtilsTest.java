package com.rtaplatform.kafka;

import org.junit.jupiter.api.Test;

import static com.rtaplatform.kafka.KafkaTestConstants.KAFKA_VALID_MESSAGE;
import static com.rtaplatform.kafka.KafkaUtils.isValidMessage;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class KafkaUtilsTest {
    @Test
    public void whenStringContainsUUIDJsonThenTrue() {
        assertTrue(isValidMessage(KAFKA_VALID_MESSAGE));
    }

    @Test
    public void whenStringNoContainsUUIDJsonThenFalse() {
        final String message = "{\"created\":  1694697252,\"uuid\" :    \"W85a5402-1d01-423b-89d4-e441350904f2\"   ff,\"user_id\": 1,\"product_id\": 1,\"time_sec\": 60}";
        assertFalse(isValidMessage(message));
    }
}
