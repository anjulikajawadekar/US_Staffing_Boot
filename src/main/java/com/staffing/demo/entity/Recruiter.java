package com.staffing.demo.entity;

import java.util.List;
import java.util.Set;

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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer recruiter_id;
	private String recruiter_name;
	private String recruiter_email;
	private String role;
	private String password;
	
	@OneToMany(mappedBy = "recruiter")
	@JsonIgnore
	private List<Requisition> requisiton;
	
	@OneToMany(mappedBy = "recruiter")
	@JsonIgnore
	private List<Candidate> candidate;
	
	@OneToMany(mappedBy = "recruiter")
	@JsonIgnore
	private List<Status> status;

	public Recruiter() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * @ManyToMany
	 * 
	 * @JoinTable( name = "requsition_recruiter", joinColumns = @JoinColumn(name =
	 * "recruiter_id"), inverseJoinColumns = @JoinColumn(name = "requisition_id"))
	 * private List<Requisition> likedRequistion;
	 */

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

	public List<Requisition> getRequisiton() {
		return requisiton;
	}

	public void setRequisiton(List<Requisition> requisiton) {
		this.requisiton = requisiton;
	}

	public List<Candidate> getCandidate() {
		return candidate;
	}

	public void setCandidate(List<Candidate> candidate) {
		this.candidate = candidate;
	}

	public List<Status> getStatus() {
		return status;
	}

	public void setStatus(List<Status> status) {
		this.status = status;
	}
	
	

	/*
	 * public List<Requisition> getLikedRequistion() { return likedRequistion; }
	 * 
	 * 
	 * 
	 * public void setLikedRequistion(List<Requisition> likedRequistion) {
	 * this.likedRequistion = likedRequistion; }
	 */

}
