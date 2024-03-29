package com.staffing.demo.controller;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.staffing.demo.entity.Candidate;
import com.staffing.demo.entity.Recruiter;
import com.staffing.demo.entity.Requisition;
import com.staffing.demo.entity.StatusTbl;
import com.staffing.demo.entity_fd.Client;
import com.staffing.demo.entity_fd.Duration;
import com.staffing.demo.entity_fd.PositionType;
import com.staffing.demo.entity_fd.RateTerm;
import com.staffing.demo.entity_fd.Requisitor_fd;
import com.staffing.demo.entity_fd.Status_fd;
import com.staffing.demo.entity_fd.VisaType;
import com.staffing.demo.repository.CandidateRepo;
import com.staffing.demo.repository.RecruiterRepo;
import com.staffing.demo.repository.StatusTblRepo;
import com.staffing.demo.service.ServiceFirst;

@CrossOrigin
@RestController
public class ControllerFirst {

	@Autowired
	ServiceFirst serviceFirst;

	@Autowired
	RecruiterRepo recruiterRepo;

	@Autowired
	StatusTblRepo statustblRepo;

	@Autowired
	CandidateRepo candidateRepo;

	@GetMapping("/home")
	public String home() {
		return "Hello there2!!!!!!!!!!";
	}

	@PostMapping("/login")
	public ResponseEntity validateEmp(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String Username, @RequestParam String Password) {

		Recruiter emp = (Recruiter) serviceFirst.validateEmp_jpa(Username, Password);
		if (emp != null) {
			return new ResponseEntity<>(emp, HttpStatus.OK);
		} else {
			return ResponseEntity.badRequest().body("false");
		}
	}

	@PostMapping(value = "/addRecruiter")
	public ResponseEntity<?> RecruiterRegistration(@RequestParam String recruiter_name,
			@RequestParam String recruiter_email, @RequestParam String password) {

		return serviceFirst.RecruiterRegistration(recruiter_name, recruiter_email, password);
	}

	@GetMapping("/getAllRequisition")
	public List<Requisition> getAllRequisition(HttpServletRequest request, HttpServletResponse response) {
		return serviceFirst.getAllRequisition();
	}
	
	

	@GetMapping("/getRequisitionByID")
	public Requisition getRquisitionByID(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String ID) {
		return serviceFirst.getRecByID(ID);
	}

	@GetMapping("/getReqByReqID")
	public Requisition getRquByReqID(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer requisitionID) {
		return serviceFirst.getReqByReqID(requisitionID);
	}

	@GetMapping("/getRecruiterbyID")
	public Recruiter getRecruiterbyID(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer recruiterID) {
		return serviceFirst.getRecruiterbyID(recruiterID);
	}

	@GetMapping("/getRecruiterbyEmail")
	public Recruiter getRecruiterbyEmail(HttpServletRequest request, HttpServletResponse response,
			@RequestParam String recruiterEmail) {
		return serviceFirst.getRecruiterbyEmail(recruiterEmail);
	}

	@PutMapping("/UpdateRecruiterProfile")
	public Recruiter UpdateRecruiterProfile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int recruiterId,   @RequestParam  String recruiterName, @RequestParam String recruiterEmail) {
		return serviceFirst.UpdateRecruiterProfile(recruiterId,  recruiterName, recruiterEmail);
	}
	
	@PutMapping("/ChangePassword")
	public Recruiter ChangePassword(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int recruiterId, String currentPass, String newPass) {
		return serviceFirst.ChangePassword(recruiterId,  currentPass, newPass);
	}

	@PutMapping("/UpdateRecruiterProfileAdmin")
	public Recruiter UpdateRecruiterProfileAdmin(HttpServletRequest request, HttpServletResponse response,
			@RequestParam int recruiterId, String newPass) {
		return serviceFirst.UpdateRecruiterProfileAdmin(recruiterId, newPass);
	}



	@GetMapping("/getAllRcruiter")
	public List<Recruiter> getRec() {
		return recruiterRepo.findAll();
	}

	@GetMapping("/getAllCandidate")
	public List<Candidate> getAllCandidate() {
		return candidateRepo.findAll();
	}
	
	
	
	/*@GetMapping("/getAllCandidate2")
	public Candidate getAllCandidate2() {
		return serviceFirst.getAllCandidate2();
	}*/

	@GetMapping("/getCandidateByID")
	public Candidate getCandidateByID(HttpServletRequest request, HttpServletResponse response,
			@RequestParam Integer candidateID) {
		return serviceFirst.getCandidateByID(candidateID);
	}

	@GetMapping("/getAllStatus")
	public List<StatusTbl> getAllStatus() throws JsonProcessingException {
		return statustblRepo.findAll();
	}

	@PostMapping(value = "/add_recruiter")
	public ResponseEntity<?> AddRecr(@RequestParam String recruiter_name, @RequestParam String recruiter_email,
			@RequestParam String password) {
		return serviceFirst.AddRecruiter(recruiter_name, recruiter_email, password);

	}

	@PostMapping(value = "/add_requsition")
	public ResponseEntity<?> AddReq(@RequestParam String requisition_from, @RequestParam String id,
			@RequestParam String client, @RequestParam String job_title, @RequestParam String duration,
			@RequestParam String client_rate, @RequestParam String location, @RequestParam String position_type,
			@RequestParam String skills, @RequestParam int recruiter_id) {

		return serviceFirst.AddRequisition(requisition_from, id, client, job_title, duration, client_rate, location,
				position_type, skills, recruiter_id);

	}

	@PutMapping(value = "/update_requsition")
	public ResponseEntity<?> UpdateReq(@RequestParam int requisition_id, @RequestParam String requisition_from,
			@RequestParam String id, @RequestParam String client, @RequestParam String job_title,
			@RequestParam String duration, @RequestParam String client_rate, @RequestParam String location,
			@RequestParam String position_type, @RequestParam String skills) {
		System.out.println("skills:  \n"+skills);

		return serviceFirst.updateRequisition(requisition_id, requisition_from, id, client, job_title, duration,
				client_rate, location, position_type, skills);

	}

	@PostMapping(value = "/add_candidate")
	public ResponseEntity<?> AddCandidate(@RequestParam String candidate_name, @RequestParam String visa_type,
			@RequestParam String rate_term, @RequestParam String submitted_rate, @RequestParam String phone,
			@RequestParam String email, @RequestParam String remark, @RequestParam String reason,
			@RequestParam int recruiter_id, @RequestParam int requisition_id) {

		return serviceFirst.AddCandidate(candidate_name, visa_type, rate_term, submitted_rate, phone, email, remark,
				reason, recruiter_id, requisition_id);

	}

	@PutMapping(value = "/update_candidate")
	public ResponseEntity<?> UpdateCandidate(@RequestParam Integer candidate_id, @RequestParam String candidate_name,
			@RequestParam String visa_type, @RequestParam String rate_term, @RequestParam String submitted_rate,
			@RequestParam String phone, @RequestParam String email, @RequestParam String remark,
			@RequestParam String reason) {

		return serviceFirst.updateCandidate(candidate_id, candidate_name, visa_type, rate_term, submitted_rate, phone,
				email, remark, reason);

	}

	@GetMapping(value = "/get_st_by2id")
	public List<?> AddCandi(@RequestParam int recruiter_id, @RequestParam int requisition_id) {

		return serviceFirst.getStatusByTwoId(recruiter_id, requisition_id);
	}

	@PostMapping("/update_status1")
	public ResponseEntity<String> Update_status1(@RequestParam int recruiter_id, @RequestParam int requisition_id,
			@RequestParam String status) {

		return serviceFirst.Update_status1(recruiter_id, requisition_id, status);

	}

	@PostMapping("/update_status2")
	public ResponseEntity<String> Update_status2(@RequestParam int recruiter_id, @RequestParam int requisition_id,
			@RequestParam int candidate_id, @RequestParam String status) {

		return serviceFirst.Update_status2(recruiter_id, requisition_id, candidate_id, status);
	}

	@PutMapping("/update_status_Admin")
	public ResponseEntity<StatusTbl> UpdateStatusByAdmin(@RequestParam Integer status_id, @RequestParam String status,
			@RequestParam String status_date) {

		return serviceFirst.updateStatusByAdmin(status_id, status, status_date);
	}

//////////////////////////

	@GetMapping("/getAllClient")
	public List<Client> getClient() {
		return serviceFirst.getClient();
	}

	@PostMapping("/AddClient")
	public ResponseEntity<String> AddClient(int requisitor_id, String client_name) {
		return serviceFirst.AddClient(requisitor_id, client_name);
	}

	@PutMapping("/UpdateClient")
	public ResponseEntity<String> UpdateClient(int client_id, String client_name) {
		return serviceFirst.UpdateClient(client_id, client_name);
	}

	@DeleteMapping("/DeleteClient")
	public Client DeleteClient(int client_id) {
		return serviceFirst.DeleteClient(client_id);
	}

	@GetMapping("/getAllDuration")
	public List<Duration> getDuration() {
		return serviceFirst.getDuration();
	}

	@PostMapping("/AddDuration")
	public ResponseEntity<String> AddDuration(String duration) {
		return serviceFirst.AddDuration(duration);
	}

	@PutMapping("/UpdateDuration")
	public ResponseEntity<String> UpdateDuration(int duration_id, String duration) {
		return serviceFirst.UpdateDuration(duration_id, duration);
	}

	@DeleteMapping("/DeleteDuration")
	public Duration DeleteDuration(int duration_id) {
		return serviceFirst.DeleteDuration(duration_id);
	}

	@GetMapping("/getAllPositionType")
	public List<PositionType> getAllPositionType() {
		return serviceFirst.getAllPositionType();
	}

	@PostMapping("/AddPositionType")
	public ResponseEntity<String> AddPositionType(String position_type) {
		return serviceFirst.AddPositionType(position_type);
	}

	@PutMapping("/UpdatePositionType")
	public ResponseEntity<String> UpdatePositionType(int position_type_id, String position_type) {
		return serviceFirst.UpdatePositionType(position_type_id, position_type);
	}

	@DeleteMapping("/DeletePositionType")
	public PositionType DeletePositionType(int position_type_id) {
		return serviceFirst.DeletePositionType(position_type_id);
	}

	@GetMapping("/getAllRateTerm")
	public List<RateTerm> getAllRateTerm() {
		return serviceFirst.getAllRateTerm();
	}

	@PostMapping("/AddRateTerm")
	public ResponseEntity<String> AddRateTerm(String rate_term) {
		return serviceFirst.AddRateTerm(rate_term);
	}

	@PutMapping("/UpdateRateTerm")
	public ResponseEntity<String> UpdateRateTerm(int rate_term_id, String rate_term) {
		return serviceFirst.UpdateRateTerm(rate_term_id, rate_term);
	}

	@DeleteMapping("/DeleteRateTerm")
	public RateTerm DeleteRateTerm(int rate_term_id) {
		return serviceFirst.DeleteRateTerm(rate_term_id);
	}

	@GetMapping("/getAllRequisitorFd")
	public List<Requisitor_fd> getAllRequisitorFd() {
		return serviceFirst.getAllRequisitorFd();
	}

	@PostMapping("/AddRequisitorFd")
	public ResponseEntity<String> AddRequisitorFd(String requisitor_fd) {
		return serviceFirst.AddRequisitorFd(requisitor_fd);
	}

	@PutMapping("/UpdateRequisitorFd")
	public ResponseEntity<String> UpdateRequisitorFd(int requisitor_id, String requisitor_fd) {
		return serviceFirst.UpdateRequisitorFd(requisitor_id, requisitor_fd);
	}

	@DeleteMapping("/DeleteRequisitorFd")
	public Requisitor_fd DeleteRequisitorFd(int requisitor_id) {
		return serviceFirst.DeleteRequisitorFd(requisitor_id);
	}

	@GetMapping("/getAllStatusFd")
	public List<Status_fd> getAllStatusFd() {
		return serviceFirst.getAllStatusFd();
	}

	@PostMapping("/AddStatusFd")
	public ResponseEntity<String> AddStatusFd(String status_fd) {
		return serviceFirst.AddStatusFd(status_fd);
	}

	@PutMapping("/UpdateStatusFd")
	public ResponseEntity<String> UpdateStatusFd(int status_fd_id, String status_fd) {
		return serviceFirst.UpdateStatusFd(status_fd_id, status_fd);
	}

	@DeleteMapping("/DeleteStatusFd")
	public Status_fd DeleteStatusFd(int status_fd_id) {
		return serviceFirst.DeleteStatusFd(status_fd_id);
	}

	@GetMapping("/getAllVisaType")
	public List<VisaType> getAllVisaType() {
		return serviceFirst.getAllVisaType();
	}

	@PostMapping("/AddVisaType")
	public ResponseEntity<String> AddVisaType(String visa_type) {
		return serviceFirst.AddVisaType(visa_type);
	}

	@PutMapping("/UpdateVisaType")
	public ResponseEntity<String> UpdateVisaType(int visa_type_id, String visa_type) {
		return serviceFirst.UpdateVisaType(visa_type_id, visa_type);
	}

	@DeleteMapping("/DeleteVisaType")
	public VisaType DeleteVisaType(int visa_type_id) {
		return serviceFirst.DeleteVisaType(visa_type_id);
	}

	@DeleteMapping("/deleteCadByAdmin")
	public Candidate DeleteCadByID(int candidate_id) {
		return serviceFirst.deleteCadByID(candidate_id);
	}

	@DeleteMapping("/deleteRequisitionByAdmin")
	public Requisition deleteRequisitionByAdmin(int requisition_id) {
		return serviceFirst.deleteRequisitionByAdmin(requisition_id);
	}

	@DeleteMapping("/deleteStatusByAdmin")
	public StatusTbl DeletStatusByID(int status_id) {
		return serviceFirst.DeletStatusByID(status_id);
	}
	
	@GetMapping(value = "/get_cls_by_Quarterly")
	public List<StatusTbl> getClsQua(@RequestParam int empid, @RequestParam String category) {
		return serviceFirst.GetRecordsByQuarterly(empid, category);
	}
	
	@GetMapping(value = "/get_cls_byDate")
	public List<StatusTbl> GetRecordsBetweenDate(@RequestParam int empid, String date1, String date2)
			throws InterruptedException, ParseException {
		return this.serviceFirst.GetRecordBetDate(empid, date1, date2);
	}
	
	@GetMapping(value = "/get_cls_by_QuarterlyAdmin")
	public List<StatusTbl> getClsQuaAdmin( @RequestParam String category,int requisition_id) {
		return serviceFirst.GetRecordsByQuarterlyAdmin(category,requisition_id);
	}
	
	@GetMapping(value = "/get_cls_by_QuarterlyAdmin2")
	public List<StatusTbl> getClsQuaAdmin2( @RequestParam String category) {
		return serviceFirst.GetRecordsByQuarterlyAdmin2(category);
	}
	
	@GetMapping(value = "/get_cls_byDateAdmin")
	public List<StatusTbl> GetRecordsBetweenDateAdmin( String date1, String date2)
			throws InterruptedException, ParseException {
		return this.serviceFirst.GetRecordBetDateAdmin( date1, date2);
	}
	
	@GetMapping(value = "/getRequisitionId")
	public List<Requisition> getRequisitionId()
			throws InterruptedException, ParseException {
		return this.serviceFirst.getRequisitionId();
	}

//	@GetMapping("/getAllCandidatecate")
//	public List<Candidate> getAllCandidatecate(@RequestParam int empid, @RequestParam String category) {
//		return serviceFirst.GetRecordsByQuarterly2(empid, category);
//	}
//	@PostMapping("/delst")
//	public StatusTbl adddelete() {
//		return serviceFirst.adddelete();
//	}
	
	
}
