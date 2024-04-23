package com.kulsin.security;

import org.springframework.stereotype.Component;

import com.kulsin.model.JwtUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JwtValidator {
	
	private String secret = "fuhrer";

	public JwtUser validate(String token) {

		/* Claims are coming from JWT*/
		JwtUser jwtUser =null;
		
		try {
			Claims claimsBody = Jwts.parser().setSigningKey(secret)
					.parseClaimsJws(token)
					.getBody();
			
			jwtUser = new JwtUser();
			jwtUser.setUsername(claimsBody.getSubject());
			jwtUser.setId(Long.parseLong((String)claimsBody.get("userId")));
			jwtUser.setRole((String) claimsBody.get("role"));

		} catch (Exception e) {
			return null;
		}
		
		return jwtUser;
	}

}
