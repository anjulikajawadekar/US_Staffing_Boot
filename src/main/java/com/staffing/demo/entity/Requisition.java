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
//	private Integer id;
	private String id;
	private String client;
	private String job_title;
	private String duration;
	private String client_rate;
	private String location;
	private String position_type;
	private String skills;
	private boolean deleted;

//	@ManyToMany(mappedBy = "likedRequistion",cascade = { CascadeType.ALL })
//	@JsonIgnore
//    private List<Recruiter> recruiter;

//	@ManyToMany()
//	@JoinTable(name = "requsition_recruiter", joinColumns = @JoinColumn(name = "requisition_id"), inverseJoinColumns = @JoinColumn(name = "recruiter_id"))
//	private List<Recruiter> likedRecruiter;

	@OneToMany(mappedBy = "requisition")
	@JsonIgnore
	private List<StatusTbl> statustbl;

	@OneToMany(mappedBy = "requisition")
	@JsonIgnore
	private List<Candidate> candidate;

	public Requisition() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Requisition(Integer requisition_id, String requisition_from, String id, String client, String job_title,
			String duration, String client_rate, String location, String position_type, String skills, boolean deleted,
			List<StatusTbl> statustbl, List<Candidate> candidate) {
		super();
		this.requisition_id = requisition_id;
		this.requisition_from = requisition_from;
		this.id = id;
		this.client = client;
		this.job_title = job_title;
		this.duration = duration;
		this.client_rate = client_rate;
		this.location = location;
		this.position_type = position_type;
		this.skills = skills;
		this.deleted = deleted;
		this.statustbl = statustbl;
		this.candidate = candidate;
	}

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

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public List<StatusTbl> getStatustbl() {
		return statustbl;
	}

	public void setStatustbl(List<StatusTbl> statustbl) {
		this.statustbl = statustbl;
	}

	public List<Candidate> getCandidate() {
		return candidate;
	}

	public void setCandidate(List<Candidate> candidate) {
		this.candidate = candidate;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

}
