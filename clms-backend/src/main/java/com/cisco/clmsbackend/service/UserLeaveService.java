package com.cisco.clmsbackend.service;

import java.util.List;

import com.cisco.clmsbackend.model.LeaveStatus;
import com.cisco.clmsbackend.model.UserLeave;

public interface UserLeaveService {
	public List<UserLeave> findUserLeaveByUsername(String username);
	public UserLeave saveUserLeave(UserLeave userLeave);
	public List<UserLeave> findAll();
	public UserLeave findUserLeaveById(long id);
	public void deleteUserLeaveById(long id);
	public List<UserLeave> findByStatus(LeaveStatus status);
}