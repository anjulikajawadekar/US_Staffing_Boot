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
public class Candidate {
	@Id
	@Column(name = "candidate_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer candidate_id;
	private String candidate_name;
	private String Visa_type;
	private String rate_term;
	private String submitted_rate;
	private String pnone;	
	private String email;
	private String remark;
	private String reason;
	
	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
//	@ManyToOne
//	@JoinColumn(name ="status_id")
//	private Status status;
//	
//	@ManyToOne
//	@JoinColumn(name ="requisition_id")
//	private Requisition requisition;	
//	
//	@ManyToOne
//	@JoinColumn(name ="recruiter_id")
//	private Recruiter recruiter;
		
}
