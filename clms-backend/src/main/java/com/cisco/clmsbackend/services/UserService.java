package com.cisco.clmsbackend.services;

import com.cisco.clmsbackend.models.User;

public interface UserService {
	public User findUserByEmail(String email);
	public void saveUser(User user);
}