package com.kulsin.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.kulsin.service.PublishService;

@RestController
public class PublisherConntroller {

	@Autowired
	private PublishService publishService;

	@GetMapping(value = "/publish/{message}")
	public String publish(@PathVariable String message) {
		publishService.publish(message);
		return "Successful";
	}
}
