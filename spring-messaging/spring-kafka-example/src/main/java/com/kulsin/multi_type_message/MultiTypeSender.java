package com.kulsin.multi_type_message;

import com.kulsin.model.Farewell;
import com.kulsin.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
public class MultiTypeSender {

    @Value(value = "${kafka.topicName}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Object> multiTypeKafkaTemplate;

    public void sendMessage(String msg) {
        multiTypeKafkaTemplate.send(topicName, new Greeting("Greetings", "World!"));
        multiTypeKafkaTemplate.send(topicName, new Farewell("Farewell", 25));
        multiTypeKafkaTemplate.send(topicName, "Simple string message");
    }

}
