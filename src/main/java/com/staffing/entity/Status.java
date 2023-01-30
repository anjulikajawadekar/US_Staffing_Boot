package com.staffing.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Status {

	@Id
	@Column(name = "status_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer status_id;
	private String status;
	private LocalDate status_date;
	
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
//	@OneToMany
//	@JoinColumn(name ="candidate_id")
//	private Candidate candidate;
//	
//	@OneToMany
//	@JoinColumn(name ="candidate_id")
//	private Requisition requisition;
	
//	@OneToMany(mappedBy = "Candidate")
//	@JsonIgnore
//	private Integer Candidate_id;
//	
//	@OneToMany(mappedBy = "Requisition")
//	@JsonIgnore
//	private Integer Requisition_id;	
	
}
