package com.staffing.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.staffing.demo.entity.Recruiter;
import com.staffing.demo.entity.Requisition;
import com.staffing.demo.entity.StatusTbl;
import com.staffing.demo.repository.RecruiterRepo;
import com.staffing.demo.service.ServiceFirst;

@CrossOrigin
@RestController
public class ControllerFirst {

	@Autowired
	ServiceFirst serviceFirst;

	@Autowired
	RecruiterRepo recruiterRepo;

	@GetMapping("/home")
	public String home() {
		return "Hello there!!!!!!!!!!";
	}

	@GetMapping("/getAllRequisition")
	public List<Requisition> getEMP_TM(HttpServletRequest request, HttpServletResponse response) {
		return serviceFirst.GetAllRecords();
	}

	@GetMapping("/getAllReq")
	public List<Requisition> getReq() {
		return this.serviceFirst.getAllRec2();
	}

	@GetMapping("/getAllRcruiter")
	public List<Recruiter> getRec() {
		return recruiterRepo.findAll();
	}

	@PostMapping(value = "/add_recruiter")
	public ResponseEntity<?> AddRecr(@RequestParam String recruiter_name, @RequestParam String recruiter_email,
			@RequestParam String role, @RequestParam String password) {
		return serviceFirst.AddRecruiter(recruiter_name, recruiter_email, role, password);

	}

	@PostMapping(value = "/add_requsition")
	public ResponseEntity<?> AddReq(@RequestParam String requisition_from, @RequestParam int id,
			@RequestParam String client, @RequestParam String job_title, @RequestParam String duration,
			@RequestParam String client_rate, @RequestParam String location, @RequestParam String position_type,
			@RequestParam String skills, @RequestParam int recruiter_id) {
		
		return serviceFirst.AddRequisition(requisition_from, id, client, job_title, duration, client_rate, location,
				position_type, skills, recruiter_id);

	}

	@PostMapping(value = "/add_candidate")
	public ResponseEntity<?> AddCandi(@RequestParam String candidate_name, @RequestParam String visa_type,
			@RequestParam String rate_term, @RequestParam String submitted_rate, @RequestParam String pnone,
			@RequestParam String email,@RequestParam String status, @RequestParam String remark, @RequestParam String reason,
			@RequestParam int recruiter_id, @RequestParam int requisition_id) {

		return serviceFirst.AddCandidate(candidate_name, visa_type, rate_term, submitted_rate, pnone, email, status,remark,
				reason, recruiter_id, requisition_id);

	}

	@GetMapping(value = "/get_st_by2id")
	public List<?> AddCandi(@RequestParam int recruiter_id, @RequestParam int requisition_id) {

		return serviceFirst.getStatusByTwoId(recruiter_id, requisition_id);
	}

	@PostMapping(value = "/updateStatus")
	public List<StatusTbl> updateStatus(@RequestParam int recruiter_id, @RequestParam int requisition_id,
			@RequestParam String status, @RequestParam int candidate_id) {

		return serviceFirst.UpdateStatusTbl(recruiter_id, requisition_id, status, candidate_id);
	}

	/*@PostMapping(value = "/addStatus1")
	public ResponseEntity<?> addStatus1(@RequestParam int recruiter_id, @RequestParam int requisition_id,
			@RequestParam String status) {

		return serviceFirst.AddStatus1(recruiter_id, requisition_id, status);
	}

	@PostMapping(value = "/addStatus2")
	public ResponseEntity<?> addStatus2(@RequestParam int recruiter_id, @RequestParam int requisition_id,
			@RequestParam String status, @RequestParam int candidate_id) {

		return serviceFirst.AddStatus2(recruiter_id, requisition_id, candidate_id, status);
	}*/

	@PostMapping("/update_status")
	public ResponseEntity<String> Update_status(@RequestParam int recruiter_id, @RequestParam int requisition_id,
			@RequestParam int candidate_id, @RequestParam String status) {
		System.out.println("hello");

		if (candidate_id == 0) {
			return serviceFirst.Update_status1(recruiter_id, requisition_id, status);
		} else {
			return serviceFirst.Update_status2(recruiter_id, requisition_id, candidate_id, status);
		}

	}

}
