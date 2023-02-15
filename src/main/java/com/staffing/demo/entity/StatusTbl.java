package com.staffing.demo.entity;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class StatusTbl {

	@Id
	@Column(name = "status_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer status_id;
	private String status;
	private boolean flag;	

	private LocalDate status_date;

	@ManyToOne
	@JoinColumn(name = "requisition_id")
	private Requisition requisition;

	@ManyToOne
	@JoinColumn(name = "recruiter_id")
	private Recruiter recruiter;

	@ManyToOne
	@JoinColumn(name = "candidate_id")
	private Candidate candidate;

	public StatusTbl() {
		super();
		// TODO Auto-generated constructor stub
	}

	public StatusTbl(Integer status_id, String status, boolean flag, LocalDate status_date, Requisition requisition,
			Recruiter recruiter, Candidate candidate) {
		super();
		this.status_id = status_id;
		this.status = status;
		this.flag = flag;
		this.status_date = status_date;
		this.requisition = requisition;
		this.recruiter = recruiter;
		this.candidate = candidate;
	}

	public Integer getStatus_id() {
		return status_id;
	}

	public void setStatus_id(Integer status_id) {
		this.status_id = status_id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getStatus_date() {
		return status_date;
	}

	public void setStatus_date(LocalDate status_date) {
		this.status_date = status_date;
	}

	public Requisition getRequisition() {
		return requisition;
	}

	public void setRequisition(Requisition requisition) {
		this.requisition = requisition;
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
}
