package com.staffing.demo.entity_fd;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status_fd {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Status_fd_id;
	private String Status_fd;
	public Status_fd() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getStatus_fd_id() {
		return Status_fd_id;
	}

	public void setStatus_fd_id(Integer status_fd_id) {
		Status_fd_id = status_fd_id;
	}

	public String getStatus_fd() {
		return Status_fd;
	}
	public void setStatus_fd(String status_fd) {
		Status_fd = status_fd;
	}

	
	
}
