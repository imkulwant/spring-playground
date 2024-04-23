package com.kulsin.service;

import java.util.List;

import com.kulsin.model.Cart;
import com.kulsin.model.Device;
import com.kulsin.model.User;

public interface DataPersistanceService {

	Device saveDevice(Device device);

	List<Device> findAllDevices();
	
	User saveUser(User user);
	
	Cart getUserCart(User user);
}
