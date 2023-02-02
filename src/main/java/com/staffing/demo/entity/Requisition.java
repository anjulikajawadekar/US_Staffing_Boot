package com.staffing.demo.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Requisition {

	@Id
	@Column(name = "requisition_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer requisition_id;
	private String requisition_from;
	private Integer id;
	private String client;
	private String job_title;
	private String duration;
	private String client_rate;
	private String location;
	private String position_type;
	private String skills;
	private String current_status;
	public Requisition() {
		super();
		// TODO Auto-generated constructor stub
	}



//	@ManyToMany(mappedBy = "likedRequistion")
//	private List<Recruiter> recruiter;

	@ManyToOne
	@JoinColumn(name = "empid")
	private Recruiter recruiter;
	
	@OneToMany(mappedBy = "requisition")
	@JsonIgnore
	private List<Candidate> candidate;
	
	@OneToMany(mappedBy = "requisition")
	@JsonIgnore
	private List<Status> status;

	

	public Integer getRequisition_id() {
		return requisition_id;
	}

	public void setRequisition_id(Integer requisition_id) {
		this.requisition_id = requisition_id;
	}

	public String getRequisition_from() {
		return requisition_from;
	}

	public void setRequisition_from(String requisition_from) {
		this.requisition_from = requisition_from;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public String getJob_title() {
		return job_title;
	}

	public void setJob_title(String job_title) {
		this.job_title = job_title;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}

	public String getClient_rate() {
		return client_rate;
	}

	public void setClient_rate(String client_rate) {
		this.client_rate = client_rate;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPosition_type() {
		return position_type;
	}

	public void setPosition_type(String position_type) {
		this.position_type = position_type;
	}

	public String getSkills() {
		return skills;
	}

	public void setSkills(String skills) {
		this.skills = skills;
	}

	public Recruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
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

	public String getCurrent_status() {
		return current_status;
	}

	public void setCurrent_status(String current_status) {
		this.current_status = current_status;
	}


//	@ManyToOne
//	@JoinColumn(name = "candidate_id")
//	private Candidate candidate;
//
//	@ManyToMany(mappedBy = "recruiter")
//	@JsonIgnore
//	private List<Recruiter> recruiters;
//	
//	@OneToMany(mappedBy = "candidate")
//	@JsonIgnore
//	private List<Candidate> candidates;

}
