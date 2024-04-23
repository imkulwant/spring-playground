package com.kulsin.custom_type_message;

import com.kulsin.model.Greeting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

//@Service
public class SendCustomMessage {

    @Value(value = "${kafka.topicName}")
    private String topicName;

    @Autowired
    private KafkaTemplate<String, Greeting> greetingKafkaTemplate;


    public void sendCustomMessage(Greeting msg) {
        greetingKafkaTemplate.send(topicName, msg);
    }


}
