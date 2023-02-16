package com.staffing.demo.entity;

import java.util.List;

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
public class Candidate {
	@Id
	@Column(name = "candidate_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer candidate_id;
	private String candidate_name;
	private String visa_type;
	private String rate_term;
	private String submitted_rate;
	private String phone;
	private String email;
	private String remark;
	private String reason;

//	@ManyToMany
//	@JoinTable(name = "requsition_candidate", joinColumns = @JoinColumn(name = "candidate_id"), inverseJoinColumns = @JoinColumn(name = "requisition_id"))
//	private List<Requisition> candidateRequistion;

	@ManyToOne
	@JoinColumn(name = "recruiter_id")
	private Recruiter recruiter;

	@ManyToOne
	@JoinColumn(name = "requisition_id")
	private Requisition requisition;

	@OneToMany(mappedBy = "candidate")
	@JsonIgnore
	private List<StatusTbl> statustbl;

	public Candidate() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Candidate(Integer candidate_id, String candidate_name, String visa_type, String rate_term,
			String submitted_rate, String phone, String email, String remark, String reason, Recruiter recruiter,
			Requisition requisition, List<StatusTbl> statustbl) {
		super();
		this.candidate_id = candidate_id;
		this.candidate_name = candidate_name;
		this.visa_type = visa_type;
		this.rate_term = rate_term;
		this.submitted_rate = submitted_rate;
		this.phone = phone;
		this.email = email;
		this.remark = remark;
		this.reason = reason;
		this.recruiter = recruiter;
		this.requisition = requisition;
		this.statustbl = statustbl;
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
		return visa_type;
	}

	public void setVisa_type(String visa_type) {
		this.visa_type = visa_type;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

	public Recruiter getRecruiter() {
		return recruiter;
	}

	public void setRecruiter(Recruiter recruiter) {
		this.recruiter = recruiter;
	}

	public List<StatusTbl> getStatustbl() {
		return statustbl;
	}

	public void setStatustbl(List<StatusTbl> statustbl) {
		this.statustbl = statustbl;
	}

	public Requisition getRequisition() {
		return requisition;
	}

	public void setRequisition(Requisition requisition) {
		this.requisition = requisition;
	}

}
