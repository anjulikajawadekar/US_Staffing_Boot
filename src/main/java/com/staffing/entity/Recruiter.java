package com.staffing.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Recruiter {
	
	@Id
	@Column(name = "recruiter_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recruiter_id;
	private String recruiter_name;
	private String recruiter_email;
	private String role;
	private String password;
	
	public Recruiter() {
		super();
		// TODO Auto-generated constructor stub
	}
	
//	@ManyToMany
//	@JoinColumn(name ="requisition_id")
//	private Requisition requisition;
	
	
}
