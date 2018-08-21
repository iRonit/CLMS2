package com.cisco.clmsbackend.service;

import java.util.List;

import com.cisco.clmsbackend.model.Admin;

public interface AdminService {

	public Admin saveAdmin(Admin admin);
	public List<Admin> findAll();
	public void deleteAdminByUsername(String username);
	public Admin findAdminByUsername(String username);
}
