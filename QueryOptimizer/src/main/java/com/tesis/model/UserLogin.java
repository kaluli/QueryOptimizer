package com.tesis.model;

import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class UserLogin {

	@NotEmpty
	@Size(min=4, max=20)
	private String user;
		
	@NotEmpty
	@Size(min=4, max=8)	
	private String password;

	public String getPassword() {
		return password;
	}

	public String getUser() {
		return user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUser(String user) {
		this.user = user;
	}	
}
