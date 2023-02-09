package com.staffing.demo.entity_fd;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Requisitor_fd {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer requisitor_id;
	private String requisitor_fd;
	
	public Requisitor_fd() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRequisitor_id() {
		return requisitor_id;
	}

	public void setRequisitor_id(Integer requisitor_id) {
		this.requisitor_id = requisitor_id;
	}

	public String getRequisitor_fd() {
		return requisitor_fd;
	}

	public void setRequisitor_fd(String requisitor_fd) {
		this.requisitor_fd = requisitor_fd;
	}

	
	
}
