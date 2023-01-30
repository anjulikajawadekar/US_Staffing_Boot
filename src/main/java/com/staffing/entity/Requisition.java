package com.staffing.entity;

import java.util.List;

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
	@Column(name = "recruiter_id")
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
	
	public Requisition() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
//	@ManyToMany
//	@JoinColumn(name ="recruiter_id")
//	private Recruiter recruiter;
//	
//	@ManyToOne
//	@JoinColumn(name = "candidate_id")
//	private Candidate candidate;

//	@ManyToMany(mappedBy = "recruiter")
//	@JsonIgnore
//	private List<Recruiter> recruiters;
//	
//	@OneToMany(mappedBy = "candidate")
//	@JsonIgnore
//	private List<Candidate> candidates;
	
}
