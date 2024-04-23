package com.kulsin.reposiotry;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.kulsin.model.User;

public interface UserRepository extends MongoRepository<User, Integer>{

	
}