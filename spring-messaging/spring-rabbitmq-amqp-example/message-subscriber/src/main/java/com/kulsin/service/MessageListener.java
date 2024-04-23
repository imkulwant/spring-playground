package com.kulsin.service;

import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class MessageListener {

	public void onMessage(String message) {
		
		System.out.println(new Date());
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Message Received:"+message);
		System.out.println(new Date());

	}
}
