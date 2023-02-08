package com.staffing.demo.entity_fd;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class VisaType {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer visa_type_id;
	private String visa_type;
	public VisaType() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Integer getVisa_type_id() {
		return visa_type_id;
	}

	public void setVisa_type_id(Integer visa_type_id) {
		this.visa_type_id = visa_type_id;
	}

	public String getVisa_type() {
		return visa_type;
	}
	public void setVisa_type(String visa_type) {
		this.visa_type = visa_type;
	}

	
	
}
