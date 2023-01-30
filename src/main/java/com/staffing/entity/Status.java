package com.staffing.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Status {

	@Id
	@Column(name = "Recruiter_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Status_id;
	private String Status;
	private LocalDate Status_date;
	
	@OneToMany(mappedBy = "Candidate")
	@JsonIgnore
	private Integer Candidate_id;
	
	@OneToMany(mappedBy = "Requisition")
	@JsonIgnore
	private Integer Requisition_id;
	
	public Status(Integer status_id, String status, LocalDate status_date, Integer candidate_id,
			Integer requisition_id) {
		super();
		Status_id = status_id;
		Status = status;
		Status_date = status_date;
		Candidate_id = candidate_id;
		Requisition_id = requisition_id;
	}
	
	public Integer getStatus_id() {
		return Status_id;
	}
	public void setStatus_id(Integer status_id) {
		Status_id = status_id;
	}
	public String getStatus() {
		return Status;
	}
	public void setStatus(String status) {
		Status = status;
	}
	public Integer getCandidate_id() {
		return Candidate_id;
	}
	public LocalDate getStatus_date() {
		return Status_date;
	}

	public void setStatus_date(LocalDate status_date) {
		Status_date = status_date;
	}

	public Integer getRequisition_id() {
		return Requisition_id;
	}

	public void setRequisition_id(Integer requisition_id) {
		Requisition_id = requisition_id;
	}

	public void setCandidate_id(Integer candidate_id) {
		Candidate_id = candidate_id;
	}	
}
