package com.staffing.demo.entity_fd;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PositionType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer position_type_id;
	private String position_type;
	
	public PositionType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getPosition_type_id() {
		return position_type_id;
	}

	public void setPosition_type_id(Integer position_type_id) {
		this.position_type_id = position_type_id;
	}

	public String getPosition_type() {
		return position_type;
	}

	public void setPosition_type(String position_type) {
		this.position_type = position_type;
	}

	
	
}
