package com.staffing.demo.entity_fd;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class RateTerm {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer rate_term_id;
	private String rate_term;

	public RateTerm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getRate_term_id() {
		return rate_term_id;
	}

	public void setRate_term_id(Integer rate_term_id) {
		this.rate_term_id = rate_term_id;
	}

	public String getRate_term() {
		return rate_term;
	}

	public void setRate_term(String rate_term) {
		this.rate_term = rate_term;
	}

}
