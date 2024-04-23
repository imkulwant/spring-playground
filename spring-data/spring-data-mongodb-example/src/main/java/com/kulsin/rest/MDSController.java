package com.kulsin.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.kulsin.model.Cart;
import com.kulsin.model.Device;
import com.kulsin.model.User;
import com.kulsin.service.DataPersistanceService;

@RestController
@RequestMapping("/service")
public class MDSController {

	@Autowired
	DataPersistanceService dataPersistanceService;

	@RequestMapping(value = "/device", method = RequestMethod.POST)
	public Device save(@RequestBody Device device) {
		return dataPersistanceService.saveDevice(device);
	}

	@RequestMapping(value = "/device", method = RequestMethod.GET)
	@ResponseBody
	public List<Device> getAllDevices() {
		return dataPersistanceService.findAllDevices();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public User save(@RequestBody User user) {
		return dataPersistanceService.saveUser(user);
	}

	@RequestMapping(value = "/cart", method = RequestMethod.GET)
	public Cart getUserCart(@RequestBody User user) {
		return dataPersistanceService.getUserCart(user);
	}

}