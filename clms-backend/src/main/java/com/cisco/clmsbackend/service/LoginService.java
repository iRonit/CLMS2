package com.cisco.clmsbackend.service;

public interface LoginService {

	public String authenticate(String username, String password);
	public boolean authenticateLDAP(String username, String password);
	public String getJWT(String username);
}
