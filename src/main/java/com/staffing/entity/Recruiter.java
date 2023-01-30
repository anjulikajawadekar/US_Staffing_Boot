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
	@Column(name = "Recruiter_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Recruiter_id;
	private String Recruiter_name;
	private String Recruiter_email;
	private String Role;
	private String Password;
	
	@ManyToMany
	@JoinColumn(name ="Requisition_id")
	private Requisition requisition;
	
	public Recruiter(Integer recruiter_id, String recruiter_name, String recruiter_email, String role, String password,
			Requisition requisition) {
		super();
		Recruiter_id = recruiter_id;
		Recruiter_name = recruiter_name;
		Recruiter_email = recruiter_email;
		Role = role;
		Password = password;
		this.requisition = requisition;
	}
	
	public Integer getRecruiter_id() {
		return Recruiter_id;
	}
	public void setRecruiter_id(Integer recruiter_id) {
		Recruiter_id = recruiter_id;
	}
	public String getRecruiter_name() {
		return Recruiter_name;
	}
	public void setRecruiter_name(String recruiter_name) {
		Recruiter_name = recruiter_name;
	}
	public String getRecruiter_email() {
		return Recruiter_email;
	}
	public void setRecruiter_email(String recruiter_email) {
		Recruiter_email = recruiter_email;
	}
	public String getRole() {
		return Role;
	}
	public void setRole(String role) {
		Role = role;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}

	public Requisition getRequisition() {
		return requisition;
	}

	public void setRequisition(Requisition requisition) {
		this.requisition = requisition;
	}	
	
}
