package com.staffing.demo.entity_fd;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Duration {
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer duration_id;
	private String duration;
	
	public Duration() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getDuration_id() {
		return duration_id;
	}
	public void setDuration_id(Integer duration_id) {
		this.duration_id = duration_id;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	
	
	
}
