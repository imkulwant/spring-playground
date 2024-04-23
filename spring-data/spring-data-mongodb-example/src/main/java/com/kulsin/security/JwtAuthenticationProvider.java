package com.kulsin.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.kulsin.model.JwtAuthenticationToken;
import com.kulsin.model.JwtUser;
import com.kulsin.model.JwtUserDetails;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

	@Autowired
	private JwtValidator jwtValidator;
	
	@Override
	public boolean supports(Class<?> authenticationClass) {
		return JwtAuthenticationToken.class.isAssignableFrom(authenticationClass);
	}

	@Override
	protected void additionalAuthenticationChecks(UserDetails userDetails,
			UsernamePasswordAuthenticationToken authentication) throws AuthenticationException {
		// TODO Auto-generated method stub

	}

	@Override
	protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken authenticationToken)
			throws AuthenticationException {
		JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) authenticationToken;
		String token = jwtAuthenticationToken.getToken();
		JwtUser jwtUser = jwtValidator.validate(token);
		
		if(jwtUser == null) {
			throw new RuntimeException("JWT Token is missing");
		}
		
		List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(jwtUser.getRole());

		
		return new JwtUserDetails(jwtUser.getUserName(), token, jwtUser.getId(), authorities );
	}

}
