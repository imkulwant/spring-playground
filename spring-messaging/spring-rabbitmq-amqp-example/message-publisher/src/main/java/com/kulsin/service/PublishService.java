package com.kulsin.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PublishService {

	private static String ROUTING_KEY = "message.app";

	@Autowired
	private RabbitTemplate rabbitTemplate;

	public void publish(String message) {
		rabbitTemplate.convertAndSend(ROUTING_KEY, message);
	}
}
