package com.cisco.clmsbackend.service;

import java.util.List;

public interface LoginService {

	public List<String> authenticate(String username, String password);
	public boolean authenticateLDAP(String username, String password);
	public List<String> getCompoundedJWT(String username);
}
