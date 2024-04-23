package com.kulsin.security;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.kulsin.model.JwtAuthenticationToken;

public class JwtAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter{

	protected JwtAuthenticationTokenFilter(RequestMatcher requiresAuthenticationRequestMatcher) {
		super(requiresAuthenticationRequestMatcher);
	}
	
	protected JwtAuthenticationTokenFilter() {
		super("/service/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		String header = request.getHeader("Authorization");
		
		if(header ==null || !header.startsWith("Token ")) {
			throw new RuntimeException("JWT Token is missing");
		}
		
		String authenticationToken = header.substring(6);
		
		JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(authenticationToken);
		return getAuthenticationManager().authenticate(jwtAuthenticationToken);
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}
	
	

}
