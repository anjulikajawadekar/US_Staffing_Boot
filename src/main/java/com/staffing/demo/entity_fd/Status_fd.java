package com.staffing.demo.entity_fd;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Status_fd {
	
	@Id
	private String Status_fd;

	public Status_fd(String status_fd) {
		super();
		Status_fd = status_fd;
	}

	public String getStatus_fd() {
		return Status_fd;
	}

	public void setStatus_fd(String status_fd) {
		Status_fd = status_fd;
	}
}
