package com.staffing.demo.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Recruiter {

	@Id
	@Column(name = "recruiter_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer recruiter_id;
	private String recruiter_name;
	private String recruiter_email;
	private String role;
	private String password;

//	@ManyToMany(cascade = { CascadeType.ALL })
//	@JoinTable(name = "requsition_recruiter", joinColumns = @JoinColumn(name = "recruiter_id"), inverseJoinColumns = @JoinColumn(name = "requisition_id"))
//	private List<Requisition> likedRequistion;

//	@ManyToMany(mappedBy = "likedRecruiter")
//	@JsonIgnore
//	private List<Requisition> requisition;

	@OneToMany(mappedBy = "recruiter")
	@JsonIgnore
	private List<Candidate> candidate;

	@OneToMany(mappedBy = "recruiter")
	@JsonIgnore
	private List<StatusTbl> statustbl;

	public Recruiter() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Recruiter(Integer recruiter_id, String recruiter_name, String recruiter_email, String role, String password,
			List<Candidate> candidate, List<StatusTbl> statustbl) {
		super();
		this.recruiter_id = recruiter_id;
		this.recruiter_name = recruiter_name;
		this.recruiter_email = recruiter_email;
		this.role = role;
		this.password = password;
		this.candidate = candidate;
		this.statustbl = statustbl;
	}

	public Integer getRecruiter_id() {
		return recruiter_id;
	}

	public void setRecruiter_id(Integer recruiter_id) {
		this.recruiter_id = recruiter_id;
	}

	public String getRecruiter_name() {
		return recruiter_name;
	}

	public void setRecruiter_name(String recruiter_name) {
		this.recruiter_name = recruiter_name;
	}

	public String getRecruiter_email() {
		return recruiter_email;
	}

	public void setRecruiter_email(String recruiter_email) {
		this.recruiter_email = recruiter_email;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Candidate> getCandidate() {
		return candidate;
	}

	public void setCandidate(List<Candidate> candidate) {
		this.candidate = candidate;
	}

	public List<StatusTbl> getStatustbl() {
		return statustbl;
	}

	public void setStatustbl(List<StatusTbl> statustbl) {
		this.statustbl = statustbl;
	}

}
