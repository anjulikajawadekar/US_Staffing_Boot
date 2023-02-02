package com.staffing.demo.entity_fd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Duration {
	
	public Duration() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Id
//	@Column(name = "duration_id")
//	private Integer duration_id;
	private String duration;
	
}
