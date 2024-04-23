package com.kulsin.custom_type_message;

import com.kulsin.model.Greeting;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

//@Service
public class CustomMessageConsumer {

    @KafkaListener(
            topics = "topicName",
            containerFactory = "greetingKafkaListenerContainerFactory")
    public void greetingListener(Greeting greeting) {
        // process greeting message
    }

}
