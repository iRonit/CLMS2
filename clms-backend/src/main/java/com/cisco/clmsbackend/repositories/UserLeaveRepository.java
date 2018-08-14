package com.cisco.clmsbackend.repositories;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cisco.clmsbackend.models.LeaveStatus;
import com.cisco.clmsbackend.models.UserLeave;

@Repository("userRepository")
public interface UserLeaveRepository extends JpaRepository<UserLeave, Long> {
	 List<UserLeave> findByUsername(String username);
	 UserLeave findById(long id);
	 List<UserLeave> findByStatus(LeaveStatus status);
	 @Transactional
	 void deleteById(Long id);
}