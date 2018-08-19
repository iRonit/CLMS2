package com.cisco.clmsbackend.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cisco.clmsbackend.payload.JwtAuthenticationResponse;
import com.cisco.clmsbackend.payload.LoginRequest;
import com.cisco.clmsbackend.service.LoginService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class LoginController {

	@Autowired
	private LoginService loginService;

	@PostMapping("/auth")
	public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {
		System.out.println("LOGIN HITTTTTTTTTTTTTT");
		List<String> jwt = loginService.authenticate(loginRequest.getUsername(), loginRequest.getPassword());
		if(jwt != null) {
			return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
		} else {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
			
	}

}