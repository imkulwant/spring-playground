package com.kulsin.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kulsin.model.JwtUser;
import com.kulsin.security.JwtGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {

	public TokenController(JwtGenerator jwtGenerator) {
		this.jwtGenerator = jwtGenerator;
	}

	private JwtGenerator jwtGenerator;

	@PostMapping
	public String generate(@RequestBody final JwtUser user) {

		return jwtGenerator.generate(user);
	}
}
