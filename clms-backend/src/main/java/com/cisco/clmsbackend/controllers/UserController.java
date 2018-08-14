package com.cisco.clmsbackend.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cisco.clmsbackend.models.LeaveStatus;
import com.cisco.clmsbackend.models.UserLeave;
import com.cisco.clmsbackend.services.UserLeaveService;
import com.fasterxml.jackson.core.JsonProcessingException;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserLeaveService userLeaveService;

	@PostMapping("/")
	public ResponseEntity<String> addUserLeave(@Valid @RequestBody final UserLeave userLeave, Errors errors) {
		System.out.println("---------------->USERLEAVE/ADD Hit");

		if (errors.hasErrors()) {
			return ResponseEntity.badRequest().build();
		}
		//Cannot have status and remark set by user
		userLeave.setStatus(LeaveStatus.PENDING);
		userLeave.setRemark(null);
		
		userLeaveService.saveUserLeave(userLeave);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/{username}")
	public ResponseEntity<List<UserLeave>> getUserLeave(@PathVariable final String username) {
		System.out.println("USERLEAVE/{username} HITTTTTTTTTTTTTT");
		List<UserLeave> ul = userLeaveService.findUserLeaveByUsername(username);
		if(ul.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(ul);
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateUserLeave(@PathVariable final String id, 
			@Valid @RequestBody UserLeave userLeave) throws JsonProcessingException {
		UserLeave ul = userLeaveService.findUserLeaveById(Long.parseLong(id));
		if (ul == null)
			return ResponseEntity.notFound().build();
		ul.setFromDate(userLeave.getFromDate());
		ul.setToDate(userLeave.getToDate());
		ul.setReason(userLeave.getReason());
		userLeaveService.saveUserLeave(ul);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> removeUserLeave(@PathVariable final String id) {
		if(userLeaveService.findUserLeaveById(Long.parseLong(id)).getStatus() == LeaveStatus.PENDING)
			userLeaveService.deleteUserLeaveById(Long.parseLong(id));
		else
			return ResponseEntity.badRequest().build();
		return ResponseEntity.ok().build();
	}

}
