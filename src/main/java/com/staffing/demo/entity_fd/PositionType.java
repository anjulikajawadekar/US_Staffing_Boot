package com.staffing.demo.entity_fd;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PositionType {
	
	@Id
	private String position_type;

	public PositionType(String position_type) {
		super();
		this.position_type = position_type;
	}

	public String getPosition_type() {
		return position_type;
	}

	public void setPosition_type(String position_type) {
		this.position_type = position_type;
	}
}
