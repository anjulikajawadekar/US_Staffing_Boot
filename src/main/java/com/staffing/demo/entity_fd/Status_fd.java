package com.staffing.demo.entity_fd;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Status_fd {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer status_fd_id;
	private String status_fd;
	public Status_fd() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getStatus_fd_id() {
		return status_fd_id;
	}
	public void setStatus_fd_id(Integer status_fd_id) {
		this.status_fd_id = status_fd_id;
	}
	public String getStatus_fd() {
		return status_fd;
	}
	public void setStatus_fd(String status_fd) {
		this.status_fd = status_fd;
	}
	
	

	
	
}
