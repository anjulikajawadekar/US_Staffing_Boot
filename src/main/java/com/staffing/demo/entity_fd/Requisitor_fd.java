package com.staffing.demo.entity_fd;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Requisitor_fd {
	
	@Id
	private String requisitor_fd;

	public Requisitor_fd(String requisitor_fd) {
		super();
		this.requisitor_fd = requisitor_fd;
	}

	public String getRequisitor_fd() {
		return requisitor_fd;
	}

	public void setRequisitor_fd(String requisitor_fd) {
		this.requisitor_fd = requisitor_fd;
	}	
}
