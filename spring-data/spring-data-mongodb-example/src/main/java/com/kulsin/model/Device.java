package com.kulsin.model;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Device {

	private String id;
	private String type;
	private String brand;
	private String name;
	private String color;
	private double price;
	
	
}
