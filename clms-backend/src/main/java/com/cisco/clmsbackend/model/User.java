package com.cisco.clmsbackend.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;


public class User {
	private String username;
	private Set<? extends GrantedAuthority> roles = new HashSet<>();

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Set<? extends GrantedAuthority> getRoles() {
		return this.roles;
	}

	public void setRoles(Set<? extends GrantedAuthority> roles) {
		this.roles = roles;
	}
	
}
