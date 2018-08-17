package com.cisco.clmsbackend.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@Entity
@Table(name = "user_leave")
@JsonIgnoreProperties(value = {"appliedDate"}, allowGetters = true)
@EntityListeners(AuditingEntityListener.class)
public class UserLeave {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	
	@Column(name = "username")
	@NotBlank
	private String username;
	
	@Column(name = "applied_date", nullable=false, updatable=false)
	@Temporal(TemporalType.DATE)
	@CreatedDate
	private Date appliedDate;
	
	@Column(name = "from_date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date fromDate;
	
	@Column(name = "to_date", nullable=false)
	@Temporal(TemporalType.DATE)
	private Date toDate;
	
	@Column(name = "reason")
	@NotBlank
	@Size(max=100, message="Should have at the most 100 characters.")
	private String reason;
	
	@Column(name = "status", nullable=false)
	@Enumerated(EnumType.STRING)
	private LeaveStatus status;
	
	@Column(name = "remark")
	@Size(max=100, message="Should not exceed 100 characters.")
	private String remark;

	public Long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Date getAppliedDate() {
		return appliedDate;
	}

	public void setAppliedDate(Date appliedDate) {
		this.appliedDate = appliedDate;
	}

	public Date getFromDate() {
		return fromDate;
	}

	public void setFromDate(Date fromDate) {
		this.fromDate = fromDate;
	}

	public Date getToDate() {
		return toDate;
	}

	public void setToDate(Date toDate) {
		this.toDate = toDate;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public LeaveStatus getStatus() {
		return status;
	}

	public void setStatus(LeaveStatus status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
