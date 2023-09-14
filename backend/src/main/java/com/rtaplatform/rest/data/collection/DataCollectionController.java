package com.rtaplatform.rest.data.collection;

import com.rtaplatform.data.produce.KafkaProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController("data-collection")
@RequestMapping(value = "/data-collection")
public class DataCollectionController {
    private final KafkaProducer kafkaProducer;

    @Autowired
    public DataCollectionController(final KafkaProducer kafkaProducer) {
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping
    public void sendMessage(@RequestParam final String message) {
        kafkaProducer.sendMessage(message);
    }
}
