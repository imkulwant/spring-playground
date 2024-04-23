package com.kulsin.model;

public class JwtUser {

	private long userId;
	private String role;
	private String userName;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getId() {
		return userId;
	}

	public String getRole() {
		return role;
	}

	public void setUsername(String username) {
		this.userName = username;
	}

	public void setId(long id) {
		this.userId = id;
	}

	public void setRole(String role) {
		this.role = role;
	}

}
