package com.staffing.demo.entity;

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
	
	/*@ManyToOne
	@JoinColumn(name ="status_id")
	private Status status;*/
	
	@ManyToOne
	@JoinColumn(name ="requisition_id")
	private Requisition requisition;	
	
	@ManyToOne
	@JoinColumn(name ="recruiter_id")
	private Recruiter recruiter;
	
	@OneToMany(mappedBy = "requisition")
	@JsonIgnore
	private List<Status> status;
	
	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getCandidate_id() {
		return candidate_id;
	}

	public void setCandidate_id(Integer candidate_id) {
		this.candidate_id = candidate_id;
	}

	public String getCandidate_name() {
		return candidate_name;
	}

	public void setCandidate_name(String candidate_name) {
		this.candidate_name = candidate_name;
	}

	public String getVisa_type() {
		return Visa_type;
	}

	public void setVisa_type(String visa_type) {
		Visa_type = visa_type;
	}

	public String getRate_term() {
		return rate_term;
	}

	public void setRate_term(String rate_term) {
		this.rate_term = rate_term;
	}

	public String getSubmitted_rate() {
		return submitted_rate;
	}

	public void setSubmitted_rate(String submitted_rate) {
		this.submitted_rate = submitted_rate;
	}

	public String getPnone() {
		return pnone;
	}

	public void setPnone(String pnone) {
		this.pnone = pnone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	/*public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}*/

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

	public List<Status> getStatus() {
		return status;
	}

	public void setStatus(List<Status> status) {
		this.status = status;
	}
	
	
		
}
