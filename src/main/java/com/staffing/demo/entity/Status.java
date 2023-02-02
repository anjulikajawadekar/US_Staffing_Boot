package com.staffing.demo.entity;

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
	private boolean flag;
	public Status() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@ManyToOne
	@JoinColumn(name ="candidate_id")
	private Candidate candidate;
	
	@ManyToOne
	@JoinColumn(name = "requisition_id")
	private Requisition requisition;
	
	@ManyToOne
	@JoinColumn(name = "recruiter_id")
	private Recruiter recruiter;
	
	
	

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

	public Candidate getCandidate() {
		return candidate;
	}

	public void setCandidate(Candidate candidate) {
		this.candidate = candidate;
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

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	
	
	


	
	
	
	
	
	
}
