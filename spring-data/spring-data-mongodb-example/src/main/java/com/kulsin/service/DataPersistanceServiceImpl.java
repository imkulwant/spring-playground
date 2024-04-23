package com.kulsin.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kulsin.model.Cart;
import com.kulsin.model.Device;
import com.kulsin.model.User;
import com.kulsin.reposiotry.DeviceRepository;
import com.kulsin.reposiotry.UserRepository;

@Service
public class DataPersistanceServiceImpl implements DataPersistanceService {

	@Autowired
	private DeviceRepository deviceRepository;

	@Autowired
	private UserRepository userRepository;

	@Override
	public Device saveDevice(Device device) {
		return deviceRepository.save(device);
	}

	@Override
	public List<Device> findAllDevices() {
		return deviceRepository.findAll();
	}

	@Override
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public Cart getUserCart(User user) {
		Optional<Cart> cart = userRepository.findAll().stream().filter(u -> user.getId().equals(u.getId()))
				.map(User::getCart).findFirst();
		if (cart.isPresent()) {
			return cart.get();
		} else {
			return null;
		}
	}
}
