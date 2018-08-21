package com.cisco.clmsbackend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cisco.clmsbackend.model.Admin;
import com.cisco.clmsbackend.repository.AdminRepository;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	AdminRepository adminRepository;
	
	@Override
	public Admin saveAdmin(Admin admin) {
		return adminRepository.save(admin);
	}

	@Override
	public List<Admin> findAll() {
		return adminRepository.findAll();
	}

	@Override
	public void deleteAdminByUsername(String username) {
		adminRepository.deleteById(username);
	}
	
	@Override
	public Admin findAdminByUsername(String username) {
		return adminRepository.findByUsername(username);
	}

}
