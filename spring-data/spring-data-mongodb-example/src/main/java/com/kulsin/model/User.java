package com.kulsin.model;

import lombok.Data;

@Data
public class User {
	
	private String id;
	private String name;
	private String age;
	private String city;
	private Cart cart;

}
