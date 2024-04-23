package com.kulsin.model;

import java.util.List;

import lombok.Data;

@Data
public class Cart {

	private String id;
	private List<Device> devices;
	private double cartTotal;
}
