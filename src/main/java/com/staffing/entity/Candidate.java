package com.staffing.entity;

import java.util.List;
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
public class Candidate {
	@Id
	@Column(name = "Candidate_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Candidate_id;
	private String Candidate_name;
	private String Visa_type;
	private String Rate_term;
	private String Submitted_rate;
	private String Pnone;	
	private String Email;
	private String Remark;
	private String Reason;
	
	@ManyToOne
	@JoinColumn(name ="Status_id")
	private Status status;
	
	@ManyToOne
	@JoinColumn(name ="Requisition_id")
	private Requisition requisition;	
	
	@ManyToOne
	@JoinColumn(name ="Requisition_id")
	private Recruiter recruiter;
	
	
	public Candidate(Integer candidate_id, String candidate_name, String visa_type, String rate_term,
			String submitted_rate, String pnone, String email, String remark, String reason, Status status,
			Requisition requisition, Recruiter recruiter) {
		super();
		Candidate_id = candidate_id;
		Candidate_name = candidate_name;
		Visa_type = visa_type;
		Rate_term = rate_term;
		Submitted_rate = submitted_rate;
		Pnone = pnone;
		Email = email;
		Remark = remark;
		Reason = reason;
		this.status = status;
		this.requisition = requisition;
		this.recruiter = recruiter;
	}
	public String getRemark() {
		return Remark;
	}
	public void setRemark(String remark) {
		Remark = remark;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public Integer getCandidate_id() {
		return Candidate_id;
	}
	public void setCandidate_id(Integer candidate_id) {
		Candidate_id = candidate_id;
	}
	public String getCandidate_name() {
		return Candidate_name;
	}
	public void setCandidate_name(String candidate_name) {
		Candidate_name = candidate_name;
	}
	public String getVisa_type() {
		return Visa_type;
	}
	public void setVisa_type(String visa_type) {
		Visa_type = visa_type;
	}
	public String getRate_term() {
		return Rate_term;
	}
	public void setRate_term(String rate_term) {
		Rate_term = rate_term;
	}
	public String getSubmitted_rate() {
		return Submitted_rate;
	}
	public void setSubmitted_rate(String submitted_rate) {
		Submitted_rate = submitted_rate;
	}
	public String getPnone() {
		return Pnone;
	}
	public void setPnone(String pnone) {
		Pnone = pnone;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
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
}
