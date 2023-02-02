package com.staffing.demo.entity_fd;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class VisaType {

	@Id
	private String visa_type;

	public VisaType(String visa_type) {
		super();
		this.visa_type = visa_type;
	}

	public String getVisa_type() {
		return visa_type;
	}

	public void setVisa_type(String visa_type) {
		this.visa_type = visa_type;
	}
	
	
}
