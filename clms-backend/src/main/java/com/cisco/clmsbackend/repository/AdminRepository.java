package com.cisco.clmsbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cisco.clmsbackend.model.Admin;

@Repository("adminRepository")
public interface AdminRepository extends JpaRepository<Admin, String>{
	Admin findByUsername(String username);
}
