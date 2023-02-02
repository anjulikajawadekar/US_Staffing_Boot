package com.staffing.demo.controller;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.staffing.demo.entity.Candidate;
import com.staffing.demo.entity.Recruiter;
import com.staffing.demo.entity.Requisition;
import com.staffing.demo.entity.Status;
import com.staffing.demo.repo.RecruiterRepo;
import com.staffing.demo.repo.RequisitionRepo;
import com.staffing.demo.service.myservice;

@CrossOrigin
@RestController

public class Mycontroller {

	@Autowired
	private myservice service;

	@GetMapping("/home")
	public String home() {
		return "Hello there!!!!!!!!!!";
	}

	@PostMapping("/addreq")
	public String addReq(@RequestParam int recruiter_id,@RequestParam String requisition_from, @RequestParam int id,
			@RequestParam String client, @RequestParam String job_title, @RequestParam String duration,
			@RequestParam String client_rate, @RequestParam String location, @RequestParam String position_type,@RequestParam String skill) {
		
		return service.addReq(recruiter_id,requisition_from, id, client, job_title, duration, client_rate, location, position_type,skill);
		
	}

	@PostMapping("/addcand")
	public String addCandidate(@RequestParam int recruiter_id,@RequestParam int requisiton_id,@RequestParam String candidate_name, @RequestParam String visa_type,
			@RequestParam String rate_term, @RequestParam String submitted_rate, @RequestParam String phone,
			@RequestParam String email,@RequestParam String status, @RequestParam String remark, @RequestParam String reason) {

		return service.addcandidate(recruiter_id,requisiton_id,candidate_name, visa_type, rate_term, submitted_rate, phone,email,status, remark, reason);

	}

	@PostMapping("/addstatus")
	public String addStatus(@RequestParam int requisiton_id,@RequestParam int recruiter_id, @RequestParam int canid, @RequestParam String status
			) {

		return service.addStatus(requisiton_id,recruiter_id, canid, status);

	}

	@GetMapping("/getst")
	public List<Status> getStatus() {
		
		return service.getStatus();
		
		
	}
	
	@GetMapping("/getlastst")
	public List<Status> getLastStatus() {
		
		return service.getLastStatus();
		
		
	}
	
	@GetMapping("/getlastst1")
	public  List<Status> getLastStatus1(@RequestParam int requisiton_id,@RequestParam int recruiter_id,HttpServletRequest req) {
		int canid= Integer.parseInt(req.getParameter("canid"));
		if(canid==0)
		{
		return service.getLastStatus1(requisiton_id,recruiter_id);
		}
		else {
			return service.getLastStatus2(requisiton_id,recruiter_id, canid);
		}
		
	}
	
	
	@GetMapping("/getcan")
	public List<Candidate> getcan() {
		
		return service.getcan();
		
		
	}
	
	@PutMapping("/Updt_status")
	public String UpdateStatus(@RequestParam int requisiton_id,@RequestParam int canid,@RequestParam String status) {
		
		return service.UpdateStatus(requisiton_id,canid,status);
		
		
	}
	@PutMapping("/Updtflag")
	public String UpdateFlag(@RequestParam int status_id) {
		
		return service.UpdateStatus(status_id);
		
	}	
	
	
	
}
