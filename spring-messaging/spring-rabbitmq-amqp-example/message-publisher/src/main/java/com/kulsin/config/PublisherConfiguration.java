package com.kulsin.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PublisherConfiguration {

	private static String ROUTING_KEY = "message.app";

	@Bean
	public Queue queue() {
		return new Queue(ROUTING_KEY);
	}

	@Bean
	public TopicExchange exchange() {
		return new TopicExchange("home_message_exchange");
	}

	@Bean
	public Binding binding(Queue queue, TopicExchange exchange) {
		return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
	}
}
