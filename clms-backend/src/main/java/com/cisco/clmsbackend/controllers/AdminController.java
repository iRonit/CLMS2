package com.cisco.clmsbackend.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cisco.clmsbackend.model.Admin;
import com.cisco.clmsbackend.model.LeaveStatus;
import com.cisco.clmsbackend.model.UserLeave;
import com.cisco.clmsbackend.service.AdminService;
import com.cisco.clmsbackend.service.UserLeaveService;
import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/admin")
public class AdminController {

	@Autowired
	private UserLeaveService userLeaveService;

	@Autowired
	private AdminService adminService;

	@GetMapping("/{username}")
	public ResponseEntity<List<UserLeave>> getUserLeave(@PathVariable final String username)
			throws JsonProcessingException {
		System.out.println("USERLEAVE/{username} HITTTTTTTTTTTTTT");
		List<UserLeave> ul = userLeaveService.findUserLeaveByUsername(username);
		if (ul.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(ul);
	}

	@GetMapping("/all")
	public ResponseEntity<List<UserLeave>> getStatus(
			@RequestParam(value = "status", required = false) LeaveStatus status) {
		System.out.println("USERLEAVE/all param HITTTTTTTTTTTTTT");
		List<UserLeave> ul;
		if (status == null) {
			ul = userLeaveService.findAll();
		} else {
			ul = userLeaveService.findByStatus(status);
		}
		if (ul.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(ul);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UserLeave> updateUserLeaveAdmin(@PathVariable final String id,
			@Valid @RequestBody UserLeave userLeave) throws JsonProcessingException {
		UserLeave ul = userLeaveService.findUserLeaveById(Long.parseLong(id));
		if (ul == null)
			return ResponseEntity.notFound().build();
		LeaveStatus incomingStatus = userLeave.getStatus();
		String incomingRemark = userLeave.getRemark();
		if (incomingStatus == null || incomingRemark == null)
			return ResponseEntity.badRequest().build();
		if (ul.getStatus() == LeaveStatus.APPROVED || ul.getStatus() == LeaveStatus.REJECTED)
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		ul.setStatus(incomingStatus);
		ul.setRemark(incomingRemark);
		userLeaveService.saveUserLeave(ul);
		return ResponseEntity.ok().body(ul);
	}

	// ------------------------------ Admin related API --------------------------------------- //
	@GetMapping("/admins")
	public ResponseEntity<List<Admin>> getAdmins() {
		System.out.println("admins get HITTTTTTTTTTTTTT");
		return ResponseEntity.ok().body(adminService.findAll());
	}

	@PostMapping("/admins")
	public ResponseEntity<Admin> addAdmin(@Valid @RequestBody Admin admin) {
		System.out.println("admins add HITTTTTTTTTTTTTTTTTTTT");
		if (adminService.findAdminByUsername(admin.getUsername()) == null) {
			return ResponseEntity.ok().body(adminService.saveAdmin(admin));
		}
		return ResponseEntity.status(HttpStatus.CONFLICT).build();
	}

	@DeleteMapping("/admins/{username}")
	public ResponseEntity<?> deleteAdmin(@PathVariable final String username) {
		System.out.println("admins delete HITTTTTTTTTTTTTTTT");
		if (adminService.findAdminByUsername(username) != null) {
			System.out.println("delete----------------------->>found null--> " + username);
			adminService.deleteAdminByUsername(username);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();

	}

}
