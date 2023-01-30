package com.staffing.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Requisition {
	
	@Id
	@Column(name = "Recruiter_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Requisition_id;
	private String Requisition_from;
	private Integer ID;
	private String Client;
	private String Job_title;
	private String Duration;
	private String Client_rate;
	private String Location;
	private String Position_type;
	private String Skills;
	
	@ManyToMany
	@JoinColumn(name ="Recruiter_id")
	private Recruiter recruiter;
	
	@ManyToOne
	@JoinColumn(name = "Candidate_id")
	private Candidate candidate;
	
	public Requisition(Integer requisition_id, String requisition_from, Integer iD, String client, String job_title,
			String duration, String client_rate, String location, String position_type, String skills,
			Recruiter recruiter, Candidate candidate) {
		super();
		Requisition_id = requisition_id;
		Requisition_from = requisition_from;
		ID = iD;
		Client = client;
		Job_title = job_title;
		Duration = duration;
		Client_rate = client_rate;
		Location = location;
		Position_type = position_type;
		Skills = skills;
		this.recruiter = recruiter;
		this.candidate = candidate;
	}	
	
	public Integer getRequisition_id() {
		return Requisition_id;
	}
	public void setRequisition_id(Integer requisition_id) {
		Requisition_id = requisition_id;
	}
	public String getRequisition_from() {
		return Requisition_from;
	}
	public void setRequisition_from(String requisition_from) {
		Requisition_from = requisition_from;
	}
	public Integer getID() {
		return ID;
	}
	public void setID(Integer iD) {
		ID = iD;
	}
	public String getClient() {
		return Client;
	}
	public void setClient(String client) {
		Client = client;
	}
	public String getJob_title() {
		return Job_title;
	}
	public void setJob_title(String job_title) {
		Job_title = job_title;
	}
	public String getDuration() {
		return Duration;
	}
	public void setDuration(String duration) {
		Duration = duration;
	}
	public String getClient_rate() {
		return Client_rate;
	}
	public void setClient_rate(String client_rate) {
		Client_rate = client_rate;
	}
	public String getLocation() {
		return Location;
	}
	public void setLocation(String location) {
		Location = location;
	}
	public String getPosition_type() {
		return Position_type;
	}
	public void setPosition_type(String position_type) {
		Position_type = position_type;
	}
	public String getSkills() {
		return Skills;
	}
	public void setSkills(String skills) {
		Skills = skills;
	}
	public Recruiter getRecruiter() {
		return recruiter;
	}
	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}
	public Candidate getCandidate() {
		return candidate;
	}
	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
	}		
	
}
