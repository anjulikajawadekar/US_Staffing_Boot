package com.staffing.demo.entity_fd;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class RateTerm {

	@Id
	private String rate_term;

	public RateTerm(String rate_term) {
		super();
		this.rate_term = rate_term;
	}

	public String getRate_term() {
		return rate_term;
	}

	public void setRate_term(String rate_term) {
		this.rate_term = rate_term;
	}
	
	
}
