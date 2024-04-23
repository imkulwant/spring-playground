package com.kulsin.string_type_message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProducerResource {

    @Autowired
    private SendMessage sendMessage;

    @PostMapping(value = "/send", produces = MediaType.APPLICATION_JSON_VALUE)
    public String postMessage(
            @RequestBody Message message
    ) {

        sendMessage.sendMessage(message.getMsg());
        return "Done!";

    }
}
