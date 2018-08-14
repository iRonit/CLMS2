package com.cisco.clmsbackend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cisco.clmsbackend.models.LeaveStatus;
import com.cisco.clmsbackend.models.UserLeave;
import com.cisco.clmsbackend.repositories.UserLeaveRepository;

@Service("userLeaveService")
public class UserLeaveServiceImpl implements UserLeaveService{

	@Autowired
	private UserLeaveRepository userLeaveRepository;
	
	//@Autowired
    //private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Override
	public List<UserLeave> findUserLeaveByUsername(String username) {
		return userLeaveRepository.findByUsername(username);
	}
	
	@Override
	public UserLeave findUserLeaveById(long id) {
		return userLeaveRepository.findById(id);
	}

	@Override
	public UserLeave saveUserLeave(UserLeave userLeave) {
		return userLeaveRepository.save(userLeave);
	}

	@Override
	public List<UserLeave> findAll() {
		return userLeaveRepository.findAll();
	}

	@Override
	public void deleteUserLeaveById(long id) {
		userLeaveRepository.deleteById(id);
	}

	@Override
	public List<UserLeave> findByStatus(LeaveStatus status) {
		return userLeaveRepository.findByStatus(status);
	}
	
}