package com.staffing.demo.service;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;


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
import com.staffing.demo.repository.ClientRepo;
import com.staffing.demo.repository.DurationRepo;
import com.staffing.demo.repository.PositionTypeRepo;
import com.staffing.demo.repository.RateTermRepo;
import com.staffing.demo.repository.RecruiterRepo;
import com.staffing.demo.repository.RequisitionRepo;
import com.staffing.demo.repository.Requisitor_fdRepo;
import com.staffing.demo.repository.StatusTblRepo;
import com.staffing.demo.repository.Status_fdRepo;
import com.staffing.demo.repository.VisaTypeRepo;

@CrossOrigin
@Transactional

@Service

public class ServiceFirst {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	RequisitionRepo requisitionRepo;

	@PersistenceContext
	EntityManager entityManager;
	@Autowired
	RecruiterRepo recruiterRepo;

	@Autowired
	StatusTblRepo statusTblRepo;

	@Autowired
	ClientRepo clrepo;

	@Autowired
	DurationRepo drrepo;

	@Autowired
	PositionTypeRepo ptrepo;

	@Autowired
	RateTermRepo rtrepo;

	@Autowired
	Requisitor_fdRepo reqfdrepo;

	@Autowired
	Status_fdRepo stfdrepo;

	@Autowired
	VisaTypeRepo vtrepo;

	@Autowired
	ServiceFirst serviceFirst;

	@Autowired
	CandidateRepo candidateRepo;

	Recruiter recruiter = new Recruiter();
	Requisition requisition = new Requisition();

	StatusTbl statusTbl = new StatusTbl();
	Candidate candidate = new Candidate();

	Client cl = new Client();
	Duration dr = new Duration();
	PositionType pt = new PositionType();
	RateTerm rt = new RateTerm();
	Requisitor_fd reqfd = new Requisitor_fd();
	Status_fd stfd = new Status_fd();
	VisaType vt = new VisaType();

	// For Date Zone
	// ZoneId zoneid = ZoneId.of("America/New_York");
	// LocalDate now = LocalDate.now(zoneid);

	LocalDate now = LocalDate.now();

	DateTimeFormatter dtf_month = DateTimeFormatter.ofPattern("MMM-yyyy");

	int currentMonth = LocalDate.now().getMonthValue();
	int currentYear = LocalDate.now().getYear();
	int lastMonth = LocalDate.now().getMonthValue() - 1;

	int lastYear = LocalDate.now().getYear() - 1;

	public Recruiter validateEmp_jpa(String Username, String Password) {

		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Recruiter> cr = cb.createQuery(Recruiter.class);
		Root<Recruiter> root = cr.from(Recruiter.class);

		cr.select(root).where(cb.equal(root.get("recruiter_email"), Username),
				cb.equal(root.get("password"), Password));

		Query query = session.createQuery(cr);
		Recruiter results = (Recruiter) query.getSingleResult();
		session.close();
		return results;
	}

	public ResponseEntity<?> RecruiterRegistration(String recruiter_name, String recruiter_email, String password) {

		Session session = entityManager.unwrap(Session.class);

		Criteria crt = session.createCriteria(Recruiter.class);
		crt.add(Restrictions.eq("recruiter_email", recruiter_email));

		Recruiter z = (Recruiter) crt.uniqueResult();

		if (z != null) {
			session.close();
			return (ResponseEntity<?>) ResponseEntity.badRequest().body("User is already exist!");

		} else {
			recruiter.setRecruiter_name(recruiter_name);
			recruiter.setRecruiter_email(recruiter_email);
			recruiter.setPassword(password);
			recruiter.setRole("TM");

			session.save(recruiter);

			session.close();

			return new ResponseEntity<Recruiter>(recruiter, HttpStatus.OK);
		}
	}

	public List<Requisition> GetAllRecords() {

		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<Requisition> cr = cb.createQuery(Requisition.class);
		Root<Requisition> root = cr.from(Requisition.class);

		Query query = session.createQuery(cr);
		List<Requisition> results = query.getResultList();

		session.close();
		return results;
	}

	public List<Requisition> getAllRequisition() {

		return requisitionRepo.findAll();

	}

	public Requisition getRecByID(String ID) {
		Session session = entityManager.unwrap(Session.class);

		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<Requisition> cr = cb.createQuery(Requisition.class);
		Root<Requisition> root = cr.from(Requisition.class);

		cr.select(root).where(cb.equal(root.get("id"), ID), (cb.equal(root.get("deleted"), 1)));

		Query query = session.createQuery(cr);
		Requisition results = null;
		results = (Requisition) query.getSingleResult();

		return results;

	}

	public Requisition getReqByReqID(Integer requisitionID) {

		return requisitionRepo.getById(requisitionID);

	}

	public Recruiter getRecruiterbyID(Integer recruiterID) {

		return recruiterRepo.getById(recruiterID);
	}

	public Recruiter getRecruiterbyEmail(String recruiterEmail) {

		Session session = entityManager.unwrap(Session.class);

		Criteria crt = session.createCriteria(Recruiter.class);
		crt.add(Restrictions.eq("recruiter_email", recruiterEmail));

		Recruiter z = (Recruiter) crt.uniqueResult();
		if (z == null) {
			return (Recruiter) ResponseEntity.badRequest();

		} else {
			return z;
		}

	}

	public Recruiter UpdateRecruiterProfile(int recruiterId, String recruiterName, String recruiterEmail) {

		Session session = entityManager.unwrap(Session.class);

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Recruiter> cr = cb.createQuery(Recruiter.class);
		Root<Recruiter> root = cr.from(Recruiter.class);

		cr.select(root).where(cb.equal(root.get("recruiter_id"), recruiterId));

		Query query = session.createQuery(cr);
		Recruiter results = null;
		results = (Recruiter) query.getSingleResult();

		if (results != null) {

			recruiter = recruiterRepo.getById(recruiterId);
			recruiter.setRecruiter_name(recruiterName);
			recruiter.setRecruiter_email(recruiterEmail);

			recruiterRepo.save(recruiter);

			session.close();
			return null;
		} else {

			session.close();
			return (Recruiter) ResponseEntity.badRequest();
		}

	}

	public Recruiter ChangePassword(int recruiterId, String currentPass, String newPass) {

		Session session = entityManager.unwrap(Session.class);

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Recruiter> cr = cb.createQuery(Recruiter.class);
		Root<Recruiter> root = cr.from(Recruiter.class);

		cr.select(root).where(cb.equal(root.get("recruiter_id"), recruiterId),
				cb.equal(root.get("password"), currentPass));

		Query query = session.createQuery(cr);
		Recruiter results = null;
		results = (Recruiter) query.getSingleResult();

		if (results != null) {

			recruiter = recruiterRepo.getById(recruiterId);
			recruiter.setPassword(newPass);

			recruiterRepo.save(recruiter);

			session.close();
			return null;
		} else {

			session.close();
			return (Recruiter) ResponseEntity.badRequest();
		}

	}

	public Recruiter UpdateRecruiterProfileAdmin(int recruiterId, String newPass) {

		Session session = entityManager.unwrap(Session.class);

		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Recruiter> cr = cb.createQuery(Recruiter.class);
		Root<Recruiter> root = cr.from(Recruiter.class);

		cr.select(root).where(cb.equal(root.get("recruiter_id"), recruiterId));

		Query query = session.createQuery(cr);
		Recruiter results = null;
		results = (Recruiter) query.getSingleResult();

		if (results != null) {

			recruiter = recruiterRepo.getById(recruiterId);
			recruiter.setPassword(newPass);

			recruiterRepo.save(recruiter);

			session.close();
			return null;
		} else {

			session.close();
			return (Recruiter) ResponseEntity.badRequest();
		}
	}

	public Candidate getCandidateByID(Integer candidateID) {

		return candidateRepo.getById(candidateID);

	}

	public List<StatusTbl> getAllStatus() {

		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<StatusTbl> cr = cb.createQuery(StatusTbl.class);
		Root<StatusTbl> root = cr.from(StatusTbl.class);

		cr.select(root).where((cb.equal(root.get("flag"), 1)));

		Query query = session.createQuery(cr);
		List<StatusTbl> results = query.getResultList();
		session.close();

		return results;
	}

	public ResponseEntity<?> AddRecruiter(String recruiter_name, String recruiter_email, String password) {

		recruiter.setRecruiter_name(recruiter_name);
		recruiter.setRecruiter_email(recruiter_email);
		recruiter.setPassword(password);
		recruiter.setRole("TM");

		Session session = entityManager.unwrap(Session.class);

		session.save(recruiter);

		session.close();

		return new ResponseEntity<Recruiter>(recruiter, HttpStatus.OK);
	}

	public ResponseEntity<?> AddRequisition(String requisition_from, String id, String client, String job_title,
			String duration, String client_rate, String location, String position_type, String skills,
			int recruiter_id) {

//		ArrayList<Requisition> arrreq = new ArrayList<Requisition>();
//		ArrayList<Recruiter> arrrec = new ArrayList<Recruiter>();

		Session session = entityManager.unwrap(Session.class);

		Criteria crt = session.createCriteria(Requisition.class);
		crt.add(Restrictions.eq("id", id));

		Requisition z = (Requisition) crt.uniqueResult();

		if (z != null) {
			int a = z.getRequisition_id();

			Criteria crt1 = session.createCriteria(StatusTbl.class);
			crt1.add(Restrictions.eq("recruiter.recruiter_id", recruiter_id));
			crt1.add(Restrictions.eq("requisition.requisition_id", a));

			StatusTbl z1 = (StatusTbl) crt1.uniqueResult();

			if (z1 != null) {

				return (ResponseEntity<?>) ResponseEntity.badRequest();

			}

			statusTbl.setStatus("Requisition Assigned");
			statusTbl.setStatus_date(now);
			recruiter.setRecruiter_id(recruiter_id);
			statusTbl.setFlag(true);
			statusTbl.setRequisitionflag(true);
			statusTbl.setCandidate(null);

			/*
			 * arrreq.add(z); arrrec.add(recruiter); recruiter.setRequisition(arrreq);
			 * requisition.setLikedRecruiter(arrrec);
			 */

			statusTbl.setRecruiter(recruiter);
			statusTbl.setRequisition(z);

			session.save(statusTbl);

			session.close();

			return new ResponseEntity<Requisition>(requisition, HttpStatus.OK);

		} else {

			requisition.setRequisition_from(requisition_from);
			requisition.setId(id);
			requisition.setClient(client);
			requisition.setJob_title(job_title);
			requisition.setDuration(duration);
			requisition.setClient_rate(client_rate);
			requisition.setLocation(location);
			requisition.setPosition_type(position_type);
			requisition.setSkills(skills);
			requisition.setDeleted(true);

			statusTbl.setStatus("Requisition Assigned");
			statusTbl.setStatus_date(now);
			recruiter.setRecruiter_id(recruiter_id);
			statusTbl.setRecruiter(recruiter);
			statusTbl.setRequisition(requisition);
			statusTbl.setFlag(true);
			statusTbl.setCandidate(null);
			statusTbl.setRequisitionflag(true);

			/*
			 * arrreq.add(requisition); arrrec.add(recruiter);
			 * recruiter.setRequisition(arrreq); requisition.setLikedRecruiter(arrrec);
			 */

			session.save(requisition);
			session.save(statusTbl);

			session.close();

			return new ResponseEntity<Requisition>(requisition, HttpStatus.OK);

		}
	}

	public ResponseEntity<?> updateRequisition(int requisition_id, String requisition_from, String id, String client,
			String job_title, String duration, String client_rate, String location, String position_type,
			String skills) {

		requisition = requisitionRepo.getById(requisition_id);

		requisition.setRequisition_from(requisition_from);
		requisition.setId(id);
		requisition.setClient(client);
		requisition.setJob_title(job_title);
		requisition.setDuration(duration);
		requisition.setClient_rate(client_rate);
		requisition.setLocation(location);
		requisition.setPosition_type(position_type);
		requisition.setSkills(skills);
		Session session = entityManager.unwrap(Session.class);

		requisitionRepo.save(requisition);

		session.close();

		return new ResponseEntity<Requisition>(requisition, HttpStatus.OK);
	}

	public ResponseEntity<?> AddCandidate(String candidate_name, String visa_type, String rate_term,
			String submitted_rate, String phone, String email, String remark, String reason, int recruiter_id,
			int requisition_id) {

		Session session = entityManager.unwrap(Session.class);

		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<StatusTbl> cr = cb.createQuery(StatusTbl.class);
		Root<StatusTbl> root = cr.from(StatusTbl.class);

		cr.select(root).where((cb.equal(root.get("recruiter").get("recruiter_id"), recruiter_id)),
				(cb.equal(root.get("requisition").get("requisition_id"), requisition_id)));

		Query query = session.createQuery(cr);
		List<StatusTbl> results = query.getResultList();

		statusTblRepo.setEnabledFalse2(recruiter_id, requisition_id, 0);

		candidate.setCandidate_name(candidate_name);
		candidate.setVisa_type(visa_type);
		candidate.setRate_term(rate_term);
		candidate.setSubmitted_rate(submitted_rate);
		candidate.setPhone(phone);
		candidate.setEmail(email);
		candidate.setRemark(remark);
		candidate.setReason(reason);
		candidate.setDeleted(true);

		recruiter.setRecruiter_id(recruiter_id);
		candidate.setRecruiter(recruiter);

		requisition.setRequisition_id(requisition_id);
		candidate.setRequisition(requisition);

		statusTbl.setStatus("Submitted");
		statusTbl.setStatus_date(now);
		recruiter.setRecruiter_id(recruiter_id);
		statusTbl.setRecruiter(recruiter);

		statusTbl.setFlag(true);

		if (results.isEmpty()) {

			statusTbl.setRequisitionflag(true);

		} else {

			statusTbl.setRequisitionflag(false);
		}

		requisition.setRequisition_id(requisition_id);
		statusTbl.setRequisition(requisition);

		statusTbl.setCandidate(candidate);

		session.save(candidate);
		session.save(statusTbl);

		session.close();

		return new ResponseEntity<Candidate>(candidate, HttpStatus.OK);
	}

	public ResponseEntity<?> updateCandidate(Integer candidate_id, String candidate_name, String visa_type,
			String rate_term, String submitted_rate, String phone, String email, String remark, String reason) {

		candidate = candidateRepo.getById(candidate_id);

		candidate.setCandidate_name(candidate_name);
		candidate.setVisa_type(visa_type);
		candidate.setRate_term(rate_term);
		candidate.setSubmitted_rate(submitted_rate);
		candidate.setPhone(phone);
		candidate.setEmail(email);
		candidate.setRemark(remark);
		candidate.setReason(reason);
		Session session = entityManager.unwrap(Session.class);

		candidateRepo.save(candidate);

		session.close();

		return new ResponseEntity<Candidate>(candidate, HttpStatus.OK);
	}

//	****************get status by rec_id and rq_id*********************************************
	public List<StatusTbl> getStatusByTwoId(int recruiter_id, int requisition_id) {

		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<StatusTbl> cr = cb.createQuery(StatusTbl.class);
		Root<StatusTbl> root = cr.from(StatusTbl.class);

		cr.select(root).where((cb.equal(root.get("recruiter").get("recruiter_id"), recruiter_id)),
				((cb.equal(root.get("requisition").get("requisition_id"), requisition_id))));

		Query query = session.createQuery(cr);
		List<StatusTbl> results = query.getResultList();
		session.close();

		return results;
	}

	public void AddStatus1(int recruiter_id, int requisition_id, String status) {

		Session session = entityManager.unwrap(Session.class);
		statusTbl.setCandidate(null);
		statusTbl.setStatus(status);
		statusTbl.setStatus_date(now);
		recruiter.setRecruiter_id(recruiter_id);
		statusTbl.setRecruiter(recruiter);
		requisition.setRequisition_id(requisition_id);
		statusTbl.setRequisition(requisition);
		statusTbl.setFlag(true);
		statusTbl.setRequisitionflag(false);
		session.save(statusTbl);

		session.close();

	}

	public void AddStatus2(int recruiter_id, int requisition_id, int candidate_id, String status) {
		Session session = entityManager.unwrap(Session.class);

		statusTbl.setStatus(status);
		statusTbl.setStatus_date(now);
		recruiter.setRecruiter_id(recruiter_id);
		statusTbl.setRecruiter(recruiter);
		requisition.setRequisition_id(requisition_id);
		statusTbl.setRequisition(requisition);
		candidate.setCandidate_id(candidate_id);
		statusTbl.setCandidate(candidate);
		statusTbl.setFlag(true);
		statusTbl.setRequisitionflag(false);

		session.save(statusTbl);

		session.close();

	}

	public ResponseEntity<String> Update_status1(int recruiter_id, int requisition_id, String status) {

		statusTblRepo.setEnabledFalse2(recruiter_id, requisition_id, 0);
		AddStatus1(recruiter_id, requisition_id, status);
		return ResponseEntity.ok("Status without candidate updated successful!");
	}

	public ResponseEntity<String> Update_status2(int recruiter_id, int requisition_id, int candidate_id,
			String status) {

		statusTblRepo.setEnabledFalse2(recruiter_id, requisition_id, candidate_id);

		AddStatus2(recruiter_id, requisition_id, candidate_id, status);
		return ResponseEntity.ok("Status with candidate updated successful!");
	}

	public ResponseEntity<StatusTbl> updateStatusByAdmin(Integer status_id, String status, String status_date) {

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dt1 = LocalDate.parse(status_date, formatter);

		statusTbl = statusTblRepo.getById(status_id);
		statusTbl.setStatus(status);
		statusTbl.setStatus_date(dt1);

		statusTblRepo.save(statusTbl);

		return new ResponseEntity<StatusTbl>(statusTbl, HttpStatus.OK);
	}

	public List<Client> getClient() {
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<Client> cr = cb.createQuery(Client.class);
		Root<Client> root = cr.from(Client.class);

		Query query = session.createQuery(cr);
		List<Client> results = query.getResultList();

		session.close();
		return results;
	}

	public ResponseEntity<String> AddClient(int requisitor_id, String client_name) {
		Session session = entityManager.unwrap(Session.class);
		Criteria crt = session.createCriteria(Client.class);
		crt.add(Restrictions.eq("client_name", client_name));

		Client result = (Client) crt.uniqueResult();

		if (result != null) {
			return ResponseEntity.badRequest().body("This client name is already exist");
		} else {
			reqfd.setRequisitor_id(requisitor_id);
			cl.setRequisitor_fd(reqfd);
			cl.setClient_name(client_name);
			session.save(cl);

			session.close();
			return null;
		}
	}

	public ResponseEntity<String> UpdateClient(int client_id, String client_name) {
		Session session = entityManager.unwrap(Session.class);
		Criteria crt = session.createCriteria(Client.class);
		crt.add(Restrictions.eq("client_name", client_name));

		Client result = (Client) crt.uniqueResult();

		if (result != null) {
			return ResponseEntity.badRequest().body("This client name is already exist");
		} else {
		cl = clrepo.getById(client_id);
		cl.setClient_name(client_name);
		drrepo.save(dr);

		session.close();
		return null;
		}
	}

	public Client DeleteClient(int client_id) {
		Session session = entityManager.unwrap(Session.class);

		cl = clrepo.getById(client_id);
		clrepo.delete(cl);

		session.close();
		return null;
	}

	public List<Duration> getDuration() {
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<Duration> cr = cb.createQuery(Duration.class);
		Root<Duration> root = cr.from(Duration.class);

		Query query = session.createQuery(cr);
		List<Duration> results = query.getResultList();

		session.close();
		return results;

	}

	public ResponseEntity<String> AddDuration(String duration) {
		Session session = entityManager.unwrap(Session.class);
		Criteria crt = session.createCriteria(Duration.class);
		crt.add(Restrictions.eq("duration", duration));

		Duration result = (Duration) crt.uniqueResult();

		if (result != null) {
			return ResponseEntity.badRequest().body("This duration is already exist");
		} else {
		dr.setDuration(duration);
		session.save(dr);

		session.close();
		return null;
		}
	}

	public ResponseEntity<String> UpdateDuration(int duration_id, String duration) {
		Session session = entityManager.unwrap(Session.class);
		Criteria crt = session.createCriteria(Duration.class);
		crt.add(Restrictions.eq("duration", duration));

		Duration result = (Duration) crt.uniqueResult();

		if (result != null) {
			return ResponseEntity.badRequest().body("This duration is already exist");
		} else {
		dr = drrepo.getById(duration_id);
		dr.setDuration(duration);
		drrepo.save(dr);

		session.close();
		return null;
		}
	}

	public Duration DeleteDuration(int duration_id) {
		Session session = entityManager.unwrap(Session.class);
		
		dr = drrepo.getById(duration_id);
		drrepo.delete(dr);

		session.close();
		return null;
		
	}

	public List<PositionType> getAllPositionType() {
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<PositionType> cr = cb.createQuery(PositionType.class);
		Root<PositionType> root = cr.from(PositionType.class);

		Query query = session.createQuery(cr);
		List<PositionType> results = query.getResultList();

		session.close();
		return results;
	}

	public ResponseEntity<String> AddPositionType(String position_type) {
		Session session = entityManager.unwrap(Session.class);
		Criteria crt = session.createCriteria(PositionType.class);
		crt.add(Restrictions.eq("position_type", position_type));

		PositionType result = (PositionType) crt.uniqueResult();

		if (result != null) {
			return ResponseEntity.badRequest().body("This position type name is already exist");
		} else {
		pt.setPosition_type(position_type);
		session.save(pt);

		session.close();
		return null;
		}
	}

	public ResponseEntity<String> UpdatePositionType(int position_type_id, String position_type) {
		Session session = entityManager.unwrap(Session.class);
		Criteria crt = session.createCriteria(PositionType.class);
		crt.add(Restrictions.eq("position_type", position_type));

		PositionType result = (PositionType) crt.uniqueResult();

		if (result != null) {
			return ResponseEntity.badRequest().body("This position type name is already exist");
		} else {
		pt = ptrepo.getById(position_type_id);
		pt.setPosition_type(position_type);
		ptrepo.save(pt);

		session.close();
		return null;
		}
	}

	public PositionType DeletePositionType(int position_type_id) {
		Session session = entityManager.unwrap(Session.class);

		pt = ptrepo.getById(position_type_id);
		ptrepo.delete(pt);

		session.close();
		return null;
	}

	public List<RateTerm> getAllRateTerm() {
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<RateTerm> cr = cb.createQuery(RateTerm.class);
		Root<RateTerm> root = cr.from(RateTerm.class);

		Query query = session.createQuery(cr);
		List<RateTerm> results = query.getResultList();

		session.close();
		return results;
	}

	public ResponseEntity<String> AddRateTerm(String rate_term) {
		Session session = entityManager.unwrap(Session.class);
		Criteria crt = session.createCriteria(RateTerm.class);
		crt.add(Restrictions.eq("rate_term", rate_term));

		RateTerm result = (RateTerm) crt.uniqueResult();

		if (result != null) {
			return ResponseEntity.badRequest().body("This rate term name is already exist");
		} else {
		rt.setRate_term(rate_term);
		session.save(rt);

		session.close();
		return null;
		}
	}

	public ResponseEntity<String> UpdateRateTerm(int rate_term_id, String rate_term) {
		Session session = entityManager.unwrap(Session.class);
		
		Criteria crt = session.createCriteria(RateTerm.class);
		crt.add(Restrictions.eq("rate_term", rate_term));

		RateTerm result = (RateTerm) crt.uniqueResult();

		if (result != null) {
			return ResponseEntity.badRequest().body("This rate term name is already exist");
		} else {
		rt = rtrepo.getById(rate_term_id);
		rt.setRate_term(rate_term);
		rtrepo.save(rt);

		session.close();
		return null;
		}
	}

	public RateTerm DeleteRateTerm(int rate_term_id) {
		Session session = entityManager.unwrap(Session.class);

		rt = rtrepo.getById(rate_term_id);
		rtrepo.delete(rt);

		session.close();
		return null;
	}

	public List<Requisitor_fd> getAllRequisitorFd() {
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<Requisitor_fd> cr = cb.createQuery(Requisitor_fd.class);
		Root<Requisitor_fd> root = cr.from(Requisitor_fd.class);

		Query query = session.createQuery(cr);
		List<Requisitor_fd> results = query.getResultList();

		session.close();
		return results;
	}

	public ResponseEntity<String> AddRequisitorFd(String requisitor_fd) {
		Session session = entityManager.unwrap(Session.class);

		Criteria crt = session.createCriteria(Requisitor_fd.class);
		crt.add(Restrictions.eq("requisitor_fd", requisitor_fd));

		Requisitor_fd result = (Requisitor_fd) crt.uniqueResult();

		if (result != null) {
			return ResponseEntity.badRequest().body("This requisitor name is already exist");
		} else {
			reqfd.setRequisitor_fd(requisitor_fd);
			session.save(reqfd);

			session.close();
			return null;
		}
	}

	public ResponseEntity<String> UpdateRequisitorFd(int requisitor_id, String requisitor_fd) {
		Session session = entityManager.unwrap(Session.class);
		Criteria crt = session.createCriteria(Requisitor_fd.class);
		crt.add(Restrictions.eq("requisitor_fd", requisitor_fd));

		Requisitor_fd result = (Requisitor_fd) crt.uniqueResult();

		if (result != null) {
			return ResponseEntity.badRequest().body("This requisitor name is already exist");
		} else {
		reqfd = reqfdrepo.getById(requisitor_id);
		reqfd.setRequisitor_fd(requisitor_fd);
		reqfdrepo.save(reqfd);

		session.close();
		return null;
		}
	}

	public Requisitor_fd DeleteRequisitorFd(int requisitor_id) {
		Session session = entityManager.unwrap(Session.class);

		reqfd = reqfdrepo.getById(requisitor_id);
		reqfdrepo.delete(reqfd);

		session.close();
		return null;
	}

	public List<Status_fd> getAllStatusFd() {
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<Status_fd> cr = cb.createQuery(Status_fd.class);
		Root<Status_fd> root = cr.from(Status_fd.class);

		Query query = session.createQuery(cr);
		List<Status_fd> results = query.getResultList();

		session.close();
		return results;
	}

	public ResponseEntity<String> AddStatusFd(String status_fd) {

		Session session = entityManager.unwrap(Session.class);

		Criteria crt = session.createCriteria(Status_fd.class);
		crt.add(Restrictions.eq("status_fd", status_fd));

		Status_fd result = (Status_fd) crt.uniqueResult();

		if (result != null) {
			return ResponseEntity.badRequest().body("This status name is already exist");
		} else {
		
			stfd.setStatus_fd(status_fd);
			session.save(stfd);

			session.close();
			return null;

		}

	}

	public ResponseEntity<String> UpdateStatusFd(int status_fd_id, String status_fd) {
		Session session = entityManager.unwrap(Session.class);
		
		Criteria crt = session.createCriteria(Status_fd.class);
		crt.add(Restrictions.eq("status_fd", status_fd));

		Status_fd result = (Status_fd) crt.uniqueResult();

		if (result != null) {
			return ResponseEntity.badRequest().body("This status name is already exist");
		} else {
		
		stfd = stfdrepo.getById(status_fd_id);
		
		
		stfd.setStatus_fd(status_fd);
		session.save(stfd);

		session.close();
		return null;
		}
	}

	public Status_fd DeleteStatusFd(int status_fd_id) {
		Session session = entityManager.unwrap(Session.class);

		stfd = stfdrepo.getById(status_fd_id);
		stfdrepo.delete(stfd);

		session.close();
		return null;
	}

	public List<VisaType> getAllVisaType() {
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<VisaType> cr = cb.createQuery(VisaType.class);
		Root<VisaType> root = cr.from(VisaType.class);

		Query query = session.createQuery(cr);
		List<VisaType> results = query.getResultList();

		session.close();
		return results;
	}

	public ResponseEntity<String> AddVisaType(String visa_type) {
		Session session = entityManager.unwrap(Session.class);
		Criteria crt = session.createCriteria(VisaType.class);
		crt.add(Restrictions.eq("visa_type", visa_type));

		VisaType result = (VisaType) crt.uniqueResult();

		if (result != null) {
			return ResponseEntity.badRequest().body("This visa type name is already exist");
		} else {
		vt.setVisa_type(visa_type);
		session.save(vt);

		session.close();
		return null;
		}
	}

	public ResponseEntity<String> UpdateVisaType(int visa_type_id, String visa_type) {
		Session session = entityManager.unwrap(Session.class);
		
		Criteria crt = session.createCriteria(VisaType.class);
		crt.add(Restrictions.eq("visa_type", visa_type));

		VisaType result = (VisaType) crt.uniqueResult();

		if (result != null) {
			return ResponseEntity.badRequest().body("This visa type name is already exist");
		} else {
		vt = vtrepo.getById(visa_type_id);
		vt.setVisa_type(visa_type);
		vtrepo.save(vt);

		session.close();
		return null;
		}
	}

	public VisaType DeleteVisaType(int visa_type_id) {
		Session session = entityManager.unwrap(Session.class);

		vt = vtrepo.getById(visa_type_id);
		vtrepo.delete(vt);

		session.close();
		return null;
	}

	public Candidate deleteCadByID(int candidate_id) {

		candidate = candidateRepo.getById(candidate_id);
		candidate.setDeleted(false);
		candidateRepo.save(candidate);
		return candidate;

	}

	public Requisition deleteRequisitionByAdmin(int requisition_id) {
		requisition = requisitionRepo.getById(requisition_id);
		requisition.setDeleted(false);
		requisitionRepo.save(requisition);
		return requisition;
	}

	public StatusTbl DeletStatusByID(int status_id) {
		Session session = entityManager.unwrap(Session.class);

		statusTbl = statusTblRepo.getById(status_id);
		statusTblRepo.delete(statusTbl);

		session.close();

		return statusTbl;

	}

	public List<StatusTbl> GetRecordsByQuarterly(int empid, String category,int requisition_id) {

		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();

		int current = LocalDate.now().getMonthValue();
		System.out.println(current);

		int currentYear1 = LocalDate.now().getYear();
		int lastMonth1 = LocalDate.now().getMonthValue() - 1;

		if (lastMonth1 == 0) {
			lastMonth1 = 12;
			currentYear1 = currentYear1 - 1;
		}
		
		LocalDate a = now;
		LocalDate e = now.minusMonths(1);


		LocalDate b = now.minusMonths(3);

		LocalDate c = now.minusMonths(6);

		LocalDate d = now.minusMonths(12);


		CriteriaQuery<StatusTbl> cr = cb.createQuery(StatusTbl.class);
		Root<StatusTbl> root = cr.from(StatusTbl.class);

		if (category.equals("Current")) {

			cr.select(root).where((cb.equal(root.get("recruiter").get("recruiter_id"), empid)),
					(cb.equal(cb.function("MONTH", Integer.class, root.get("status_date")), current)),
					(cb.equal(root.get("requisition").get("requisition_id"),requisition_id)));

			Query query = session.createQuery(cr);
			List<StatusTbl> results = query.getResultList();
			System.out.println(results);
			session.close();
//			return results;

			return results;

		}

		else if (category.equals("Last_Month")) {

			cr.select(root).where((cb.equal(root.get("recruiter").get("recruiter_id"), empid)),
					(cb.equal(cb.function("MONTH", Integer.class, root.get("status_date")), lastMonth1)),
					(cb.equal(cb.function("YEAR", Integer.class, root.get("status_date")), currentYear1)),
					(cb.equal(root.get("requisition").get("requisition_id"),requisition_id)));
			Query query = session.createQuery(cr);
			List<StatusTbl> results = query.getResultList();
			System.out.println(results);
			session.close();

			return results;

		}
		
		else if (category.equals("Quarterly")) {

			cr.select(root).where((cb.equal(root.get("recruiter").get("recruiter_id"), empid)),
					(cb.between(root.get("status_date"), b, a)),
					(cb.equal(root.get("requisition").get("requisition_id"),requisition_id)));
			Query query = session.createQuery(cr);
			List<StatusTbl> results = query.getResultList();
			System.out.println(results);
			session.close();

			return results;

		}
		
		else if (category.equals("Half_yearly")) {

			cr.select(root).where((cb.equal(root.get("recruiter").get("recruiter_id"), empid)),
					(cb.between(root.get("status_date"), c, a)),
					(cb.equal(root.get("requisition").get("requisition_id"),requisition_id)));
			Query query = session.createQuery(cr);
			List<StatusTbl> results = query.getResultList();
			System.out.println(results);
			session.close();

			return results;

		}
		
		
		else if (category.equals("Yearly")) {

			cr.select(root).where((cb.equal(root.get("recruiter").get("recruiter_id"), empid)),
					(cb.between(root.get("status_date"), d, a)),
					(cb.equal(root.get("requisition").get("requisition_id"),requisition_id)));
			Query query = session.createQuery(cr);
			List<StatusTbl> results = query.getResultList();
			System.out.println(results);
			session.close();

			return results;

		}
		
		
		else if (category.equals("All")) {

			cr.select(root).where((cb.equal(root.get("recruiter").get("recruiter_id"), empid)),
					(cb.equal(root.get("requisition").get("requisition_id"),requisition_id)));
			Query query = session.createQuery(cr);
			List<StatusTbl> results = query.getResultList();
			System.out.println(results);
			session.close();

			return results;

		}

		return null;

	}

	public List<StatusTbl> GetRecordBetDate(int empid, String date1, String date2,int requisition_id) {
		
			// TODO Auto-generated method stub
			Session session = sessionFactory.openSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
		
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dt1 = LocalDate.parse(date1, formatter);
			System.out.println(dt1);
			LocalDate dt2 = LocalDate.parse(date2, formatter);
			System.out.println(dt2);

			CriteriaQuery<StatusTbl> cr = cb.createQuery(StatusTbl.class);
			Root<StatusTbl> root = cr.from(StatusTbl.class);
			cr.select(root).where(cb.equal(root.get("recruiter").get("recruiter_id"), empid),
					(cb.equal(root.get("requisition").get("requisition_id"),requisition_id)),
					(cb.between(root.get("status_date"), dt1, dt2)));
			Query query = session.createQuery(cr);
			List<StatusTbl> results = query.getResultList();

			System.out.println(results);
			session.close();

//			if(results.isEmpty()) {
			if ((results.size()) == 0) {
				return (List<StatusTbl>) ResponseEntity.badRequest();
			} else {

				return results;
			}
		
	}
	
	
	
	
	public List<StatusTbl> GetRecordsByQuarterlyAdmin(String category,int requisition_id) {

		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();

		int current = LocalDate.now().getMonthValue();
		System.out.println(current);

		int currentYear1 = LocalDate.now().getYear();
		int lastMonth1 = LocalDate.now().getMonthValue() - 1;

		if (lastMonth1 == 0) {
			lastMonth1 = 12;
			currentYear1 = currentYear1 - 1;
		}
		
		LocalDate a = now;
		LocalDate e = now.minusMonths(1);


		LocalDate b = now.minusMonths(3);

		LocalDate c = now.minusMonths(6);

		LocalDate d = now.minusMonths(12);


		CriteriaQuery<StatusTbl> cr = cb.createQuery(StatusTbl.class);
		Root<StatusTbl> root = cr.from(StatusTbl.class);

		if (category.equals("Current")) {

			cr.select(root).where((cb.equal(cb.function("MONTH", Integer.class, root.get("status_date")), current)),
					(cb.equal(root.get("requisition").get("requisition_id"),requisition_id)));

			Query query = session.createQuery(cr);
			List<StatusTbl> results = query.getResultList();
			System.out.println(results);
			session.close();
//			return results;

			return results;

		}

		else if (category.equals("Last_Month")) {

			cr.select(root).where(
					(cb.equal(cb.function("MONTH", Integer.class, root.get("status_date")), lastMonth1)),
					(cb.equal(cb.function("YEAR", Integer.class, root.get("status_date")), currentYear1)),
					(cb.equal(root.get("requisition").get("requisition_id"),requisition_id)));
			Query query = session.createQuery(cr);
			List<StatusTbl> results = query.getResultList();
			System.out.println(results);
			session.close();

			return results;

		}
		
		else if (category.equals("Quarterly")) {

			cr.select(root).where(
					(cb.between(root.get("status_date"), b, a)),
					(cb.equal(root.get("requisition").get("requisition_id"),requisition_id)));
			Query query = session.createQuery(cr);
			List<StatusTbl> results = query.getResultList();
			System.out.println(results);
			session.close();

			return results;

		}
		
		else if (category.equals("Half_yearly")) {

			cr.select(root).where(
					(cb.between(root.get("status_date"), c, a)),
					(cb.equal(root.get("requisition").get("requisition_id"),requisition_id)));
			Query query = session.createQuery(cr);
			List<StatusTbl> results = query.getResultList();
			System.out.println(results);
			session.close();

			return results;

		}
		
		
		else if (category.equals("Yearly")) {

			cr.select(root).where(
					(cb.between(root.get("status_date"), d, a)),
					(cb.equal(root.get("requisition").get("requisition_id"),requisition_id)));
			Query query = session.createQuery(cr);
			List<StatusTbl> results = query.getResultList();
			System.out.println(results);
			session.close();

			return results;

		}
		
		
		else if (category.equals("All")) {

			cr.select(root).where(
					(cb.equal(root.get("requisition").get("requisition_id"),requisition_id)));
			Query query = session.createQuery(cr);
			List<StatusTbl> results = query.getResultList();
			System.out.println(results);
			session.close();

			return results;

		}

		return null;

	}

	public List<StatusTbl> GetRecordBetDateAdmin(String date1, String date2,int requisition_id) {
		
			// TODO Auto-generated method stub
			Session session = sessionFactory.openSession();
			CriteriaBuilder cb = session.getCriteriaBuilder();
		
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			LocalDate dt1 = LocalDate.parse(date1, formatter);
			System.out.println(dt1);
			LocalDate dt2 = LocalDate.parse(date2, formatter);
			System.out.println(dt2);

			CriteriaQuery<StatusTbl> cr = cb.createQuery(StatusTbl.class);
			Root<StatusTbl> root = cr.from(StatusTbl.class);
			cr.select(root).where(
					(cb.equal(root.get("requisition").get("requisition_id"),requisition_id)),
					(cb.between(root.get("status_date"), dt1, dt2)));
			Query query = session.createQuery(cr);
			List<StatusTbl> results = query.getResultList();

			System.out.println(results);
			session.close();

//			if(results.isEmpty()) {
			if ((results.size()) == 0) {
				return (List<StatusTbl>) ResponseEntity.badRequest();
			} else {

				return results;
			}
		
	}


	/*public List<Candidate> GetRecordsByQuarterly2(int empid, String category) {
		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder cb = session.getCriteriaBuilder();

		int current = LocalDate.now().getMonthValue();

		System.out.println(current);

		int currentYear = LocalDate.now().getYear();
		int lastMonth = LocalDate.now().getMonthValue() - 1;

		if (lastMonth == 0) {
			lastMonth = 12;
			currentYear = currentYear - 1;
		}

		CriteriaQuery<Candidate> cr = cb.createQuery(Candidate.class);
		Root<Candidate> root = cr.from(Candidate.class);

		if (category.equals("Current")) {

		  //  String query = "SELECT c FROM Candidate c  LEFT JOIN c.statustbl  WHERE requisition="+reqid+" and  MONTH(status_date) ="+current+" "
		//    		+ "and YEAR(status_date) ="+currentYear+" and flag='1' ";
	    
		    String query = "SELECT c FROM Candidate c  LEFT JOIN c.statustbl  WHERE MONTH(status_date) ="+current+" "
		    		+ "and YEAR(status_date) ="+currentYear+" and status='Submitted'";
		    Query newquery = entityManager.createQuery(query, Candidate.class);
		    List<Candidate> results = newquery.getResultList();
	
			System.out.println(results);
			session.close();

			return results;

		}
		
		else if (category.equals("Last_Month")) {

		    String query = "SELECT c FROM Candidate c  JOIN c.statustbl WHERE MONTH(status_date) ="+lastMonth+" "
		    		+ "and YEAR(status_date) ="+currentYear+" and status='Submitted'";
		    Query newquery = entityManager.createQuery(query, Candidate.class);
		    List<Candidate> results = newquery.getResultList();
	
			System.out.println(results);
			session.close();

			return results;
			
		}

		else if (category.equals("All")) {

		    String query = "SELECT c FROM Candidate c LEFT JOIN c.statustbl WHERE flag='1'";
		    Query newquery = entityManager.createQuery(query, Candidate.class);
		    List<Candidate> results = newquery.getResultList();
	
			System.out.println(results);
			session.close();

			return results;
			
		}

		

		return null;

	}
*/
	

	

}
