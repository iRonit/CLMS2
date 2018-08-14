package com.cisco.clmsbackend.services;

import java.util.List;

import com.cisco.clmsbackend.models.LeaveStatus;
import com.cisco.clmsbackend.models.UserLeave;

public interface UserLeaveService {
	public List<UserLeave> findUserLeaveByUsername(String username);
	public UserLeave saveUserLeave(UserLeave userLeave);
	public List<UserLeave> findAll();
	public UserLeave findUserLeaveById(long id);
	public void deleteUserLeaveById(long id);
	public List<UserLeave> findByStatus(LeaveStatus status);
}