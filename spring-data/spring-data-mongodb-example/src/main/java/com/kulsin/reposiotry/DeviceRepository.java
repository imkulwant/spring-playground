package com.kulsin.reposiotry;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kulsin.model.Device;

public interface DeviceRepository extends MongoRepository<Device, Integer>{

	
}
