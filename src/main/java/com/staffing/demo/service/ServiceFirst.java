package com.staffing.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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
import com.staffing.demo.repository.ClientRepo;
import com.staffing.demo.repository.DurationRepo;
import com.staffing.demo.repository.PositionTypeRepo;
import com.staffing.demo.repository.RateTermRepo;
import com.staffing.demo.repository.RequisitionRepo;
import com.staffing.demo.repository.Requisitor_fdRepo;
import com.staffing.demo.repository.StatusTblRepo;
import com.staffing.demo.repository.Status_fdRepo;
import com.staffing.demo.repository.VisaTypeRepo;

@Service
public class ServiceFirst {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	RequisitionRepo requisitionRepo;

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

//	@Autowired
//	private EntityManager entityManager;

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

	LocalDate now = LocalDate.now();
	DateTimeFormatter dtf_month = DateTimeFormatter.ofPattern("MMM-yyyy");

	public Recruiter validateEmp_jpa(String Username, String Password) {

		Session session = sessionFactory.openSession();
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

	public List<Requisition> GetAllRecords() {

		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<Requisition> cr = cb.createQuery(Requisition.class);
		Root<Requisition> root = cr.from(Requisition.class);

		Query query = session.createQuery(cr);
		List<Requisition> results = query.getResultList();

		session.close();
		return results;
	}

	public List<Requisition> getAllRec2() {

		return requisitionRepo.findAll();
	}
	
	
	public List<StatusTbl> getAllStatus() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<StatusTbl> cr = cb.createQuery(StatusTbl.class);
		Root<StatusTbl> root = cr.from(StatusTbl.class);

		cr.select(root).where((cb.equal(root.get("flag"), 1)));
				

		Query query = session.createQuery(cr);
		List<StatusTbl> results = query.getResultList();
		System.out.println(results);
		session.close();

		return results;
	}

	public ResponseEntity<?> AddRecruiter(String recruiter_name, String recruiter_email, String role, String password) {

		System.out.println(
				"Value reach to Service" + recruiter_name + "  " + recruiter_email + " " + role + " " + password);

		Session hbmsession = null;
		Transaction transaction = null;

		recruiter.setRecruiter_name(recruiter_name);
		recruiter.setRecruiter_email(recruiter_email);
		recruiter.setPassword(password);
		recruiter.setRole(role);

		hbmsession = sessionFactory.openSession();
		transaction = hbmsession.beginTransaction();
		hbmsession.save(recruiter);
		transaction.commit();
		hbmsession.close();

		return new ResponseEntity<Recruiter>(recruiter, HttpStatus.OK);
	}

	public ResponseEntity<?> AddRequisition(String requisition_from, Integer id, String client, String job_title,
			String duration, String client_rate, String location, String position_type, String skills,
			int recruiter_id) {

		Session hbmsession = null;
		Transaction transaction = null;
		hbmsession = sessionFactory.openSession();
		transaction = hbmsession.beginTransaction();

		Criteria crt = hbmsession.createCriteria(Requisition.class);
		crt.add(Restrictions.eq("id", id));

		Requisition z = (Requisition) crt.uniqueResult();
		System.out.print("z = " + z);

		if (z != null) {
			int a = z.getRequisition_id();
			Criteria crt1 = hbmsession.createCriteria(StatusTbl.class);
			crt1.add(Restrictions.eq("recruiter.recruiter_id", recruiter_id));
			crt1.add(Restrictions.eq("status", "Assigned"));
			crt1.add(Restrictions.eq("requisition.requisition_id", a));
			System.out.println(a);
			StatusTbl z1 = (StatusTbl) crt1.uniqueResult();

			if (z1 != null) {
				System.out.println("record exists");
				return new ResponseEntity<StatusTbl>(statusTbl, HttpStatus.OK);
			}

			System.out.println("exist");
			statusTbl.setStatus("Assigned");
			statusTbl.setStatus_date(now);
			recruiter.setRecruiter_id(recruiter_id);
			statusTbl.setRecruiter(recruiter);
			statusTbl.setRequisition(requisition);

			hbmsession.save(statusTbl);

			transaction.commit();
			hbmsession.close();

			return new ResponseEntity<Requisition>(requisition, HttpStatus.OK);

		} else {
			System.out.println("no exist");
			requisition.setRequisition_from(requisition_from);
			requisition.setId(id);
			requisition.setClient(client);
			requisition.setJob_title(job_title);
			requisition.setDuration(duration);
			requisition.setClient_rate(client_rate);
			requisition.setLocation(location);
			requisition.setPosition_type(position_type);
			requisition.setSkills(skills);

			statusTbl.setStatus("Assigned");
			statusTbl.setStatus_date(now);
			recruiter.setRecruiter_id(recruiter_id);
			statusTbl.setRecruiter(recruiter);
			statusTbl.setRequisition(requisition);

			hbmsession.save(requisition);
			hbmsession.save(statusTbl);

			transaction.commit();
			hbmsession.close();

			return new ResponseEntity<Requisition>(requisition, HttpStatus.OK);
		}

	}

	public ResponseEntity<?> AddCandidate(String candidate_name, String visa_type, String rate_term,
			String submitted_rate, String pnone, String email, String status, String remark, String reason,
			int recruiter_id, int requisition_id) {

		Session hbmsession = null;
		Transaction transaction = null;

		candidate.setCandidate_name(candidate_name);
		candidate.setVisa_type(visa_type);
		candidate.setRate_term(rate_term);
		candidate.setSubmitted_rate(submitted_rate);
		candidate.setPnone(pnone);
		candidate.setEmail(email);
		candidate.setRemark(remark);
		candidate.setReason(reason);

		hbmsession = sessionFactory.openSession();
		transaction = hbmsession.beginTransaction();
//		hbmsession.save(cd);
		int candi2 = (Integer) hbmsession.save(candidate);

		statusTbl.setStatus(status);
		statusTbl.setStatus_date(now);
		recruiter.setRecruiter_id(recruiter_id);
		statusTbl.setRecruiter(recruiter);
		requisition.setRequisition_id(requisition_id);
		statusTbl.setRequisition(requisition);

		candidate.setCandidate_id(candi2);
		statusTbl.setCandidate(candidate);

		hbmsession.save(statusTbl);

		transaction.commit();
		hbmsession.close();

		return new ResponseEntity<Candidate>(candidate, HttpStatus.OK);
	}

//	****************get status by rec_id and rq_id*********************************************
	public List<StatusTbl> getStatusByTwoId(int recruiter_id, int requisition_id) {

		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<StatusTbl> cr = cb.createQuery(StatusTbl.class);
		Root<StatusTbl> root = cr.from(StatusTbl.class);

		cr.select(root).where((cb.equal(root.get("recruiter").get("recruiter_id"), recruiter_id)),
				((cb.equal(root.get("requisition").get("requisition_id"), requisition_id))));

		Query query = session.createQuery(cr);
		List<StatusTbl> results = query.getResultList();
		System.out.println(results);
		session.close();

		return results;

	}

//	****************End get status by rec_id and rq_id*********************************************

	public List<StatusTbl> UpdateStatusTbl(int recruiter_id, int requisition_id, String status, int candidate_id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<StatusTbl> cr = cb.createQuery(StatusTbl.class);
		Root<StatusTbl> root = cr.from(StatusTbl.class);

		cr.select(root).where((cb.equal(root.get("recruiter").get("recruiter_id"), recruiter_id)),
				((cb.equal(root.get("requisition").get("requisition_id"), requisition_id))));

		Query query = session.createQuery(cr);
		List<StatusTbl> results = query.getResultList();

		for (Iterator<StatusTbl> i = results.iterator(); i.hasNext();) {

			StatusTbl x = (StatusTbl) i.next();
			Candidate cid = x.getCandidate();
			if (cid == null) {

				int sid = x.getStatus_id();
				System.out.println("Status : " + x.getStatus_id());
				System.out.println("Status : " + x.getStatus());
				System.out.println("Status_date : " + x.getStatus_date());
				statusTbl.setStatus_id(sid);
				statusTbl.setFlag(false);
				session.saveOrUpdate(statusTbl);
//				transaction.commit();
//				transaction = session.beginTransaction();
				session.flush();
				session.clear();
			}

			System.out.println("Profile updated successful!");
		}
		session.close();
		return results;
	}

	public void AddStatus1(int recruiter_id, int requisition_id, String status) {
		// TODO Auto-generated method stub

		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		statusTbl.setStatus(status);
		statusTbl.setStatus_date(now);
		recruiter.setRecruiter_id(recruiter_id);
		statusTbl.setRecruiter(recruiter);
		requisition.setRequisition_id(requisition_id);
		statusTbl.setRequisition(requisition);
		statusTbl.setFlag(true);

		session.save(statusTbl);
		System.out.println("Status without candidate updated successful!");

		transaction.commit();
		session.close();
		// return new ResponseEntity<StatusTbl>(st, HttpStatus.OK);

	}

	public void AddStatus2(int recruiter_id, int requisition_id, int candidate_id, String status) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		statusTbl.setStatus(status);
		statusTbl.setStatus_date(now);
		recruiter.setRecruiter_id(recruiter_id);
		statusTbl.setRecruiter(recruiter);
		requisition.setRequisition_id(requisition_id);
		statusTbl.setRequisition(requisition);
		candidate.setCandidate_id(candidate_id);
		statusTbl.setCandidate(candidate);
		statusTbl.setFlag(true);

		System.out.println("Status with Candidate updated successful!");
		session.save(statusTbl);

		transaction.commit();
		session.close();

		// return new ResponseEntity<StatusTbl>(st, HttpStatus.OK);

	}

	public ResponseEntity<String> Update_status1(int recruiter_id, int requisition_id, String status) {

		statusTblRepo.setEnabledFalse1(recruiter_id, requisition_id);
		AddStatus1(recruiter_id, requisition_id, status);
		return ResponseEntity.ok("Status without candidate updated successful!");
	}

	public ResponseEntity<String> Update_status2(int recruiter_id, int requisition_id, int candidate_id,
			String status) {
		statusTblRepo.setEnabledFalse2(recruiter_id, requisition_id, candidate_id);

		AddStatus2(recruiter_id, requisition_id, candidate_id, status);
		return ResponseEntity.ok("Status with candidate updated successful!");
	}
/////////////////

	public List<Client> getClient() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<Client> cr = cb.createQuery(Client.class);
		Root<Client> root = cr.from(Client.class);

		Query query = session.createQuery(cr);
		List<Client> results = query.getResultList();

		session.close();
		return results;
	}

	public Client AddClient(String client_name) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		cl.setClient_name(client_name);
		session.save(cl);

		transaction.commit();
		session.close();
		return null;
	}

	public Client UpdateClient(int client_id, String client_name) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		cl = clrepo.getById(client_id);
		cl.setClient_name(client_name);
		drrepo.save(dr);
		t.commit();
		session.close();
		return null;
	}

	public Client DeleteClient(int client_id) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		cl = clrepo.getById(client_id);
		clrepo.delete(cl);
		t.commit();
		session.close();
		return null;
	}

	public List<Duration> getDuration() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<Duration> cr = cb.createQuery(Duration.class);
		Root<Duration> root = cr.from(Duration.class);

		Query query = session.createQuery(cr);
		List<Duration> results = query.getResultList();

		session.close();
		return results;

	}

	public Duration AddDuration(String duration) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		dr.setDuration(duration);
		session.save(dr);

		transaction.commit();
		session.close();
		return null;
	}

	public Duration UpdateDuration(int duration_id, String duration) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		dr = drrepo.getById(duration_id);
		dr.setDuration(duration);
		drrepo.save(dr);
		t.commit();
		session.close();
		return null;
	}

	public Duration DeleteDuration(int duration_id) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		dr = drrepo.getById(duration_id);
		drrepo.delete(dr);
		t.commit();
		session.close();
		return null;
	}

	public List<PositionType> getAllPositionType() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<PositionType> cr = cb.createQuery(PositionType.class);
		Root<PositionType> root = cr.from(PositionType.class);

		Query query = session.createQuery(cr);
		List<PositionType> results = query.getResultList();

		session.close();
		return results;
	}

	public PositionType AddPositionType(String position_type) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		pt.setPosition_type(position_type);
		session.save(pt);

		transaction.commit();
		session.close();
		return null;
	}

	public PositionType UpdatePositionType(int position_type_id, String position_type) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		pt = ptrepo.getById(position_type_id);
		pt.setPosition_type(position_type);
		ptrepo.save(pt);
		t.commit();
		session.close();
		return null;
	}

	public PositionType DeletePositionType(int position_type_id) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		pt = ptrepo.getById(position_type_id);
		ptrepo.delete(pt);
		t.commit();
		session.close();
		return null;
	}

	public List<RateTerm> getAllRateTerm() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<RateTerm> cr = cb.createQuery(RateTerm.class);
		Root<RateTerm> root = cr.from(RateTerm.class);

		Query query = session.createQuery(cr);
		List<RateTerm> results = query.getResultList();

		session.close();
		return results;
	}

	public RateTerm AddRateTerm(String rate_term) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		rt.setRate_term(rate_term);
		session.save(rt);

		transaction.commit();
		session.close();
		return null;
	}

	public RateTerm UpdateRateTerm(int rate_term_id, String rate_term) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		rt = rtrepo.getById(rate_term_id);
		rt.setRate_term(rate_term);
		rtrepo.save(rt);
		t.commit();
		session.close();
		return null;
	}

	public RateTerm DeleteRateTerm(int rate_term_id) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		rt = rtrepo.getById(rate_term_id);
		rtrepo.delete(rt);
		t.commit();
		session.close();
		return null;
	}

	public List<Requisitor_fd> getAllRequisitorFd() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<Requisitor_fd> cr = cb.createQuery(Requisitor_fd.class);
		Root<Requisitor_fd> root = cr.from(Requisitor_fd.class);

		Query query = session.createQuery(cr);
		List<Requisitor_fd> results = query.getResultList();

		session.close();
		return results;
	}

	public Requisitor_fd AddRequisitorFd(String requisitor_fd) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		reqfd.setRequisitor_fd(requisitor_fd);
		session.save(reqfd);

		transaction.commit();
		session.close();
		return null;
	}

	public Requisitor_fd UpdateRequisitorFd(int requisitor_id, String requisitor_fd) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		reqfd = reqfdrepo.getById(requisitor_id);
		reqfd.setRequisitor_fd(requisitor_fd);
		reqfdrepo.save(reqfd);
		t.commit();
		session.close();
		return null;
	}

	public Requisitor_fd DeleteRequisitorFd(int requisitor_id) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		reqfd = reqfdrepo.getById(requisitor_id);
		reqfdrepo.delete(reqfd);
		t.commit();
		session.close();
		return null;
	}

	public List<Status_fd> getAllStatusFd() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<Status_fd> cr = cb.createQuery(Status_fd.class);
		Root<Status_fd> root = cr.from(Status_fd.class);

		Query query = session.createQuery(cr);
		List<Status_fd> results = query.getResultList();

		session.close();
		return results;
	}

	public Status_fd AddStatusFd(String status_fd) {
		System.out.println(status_fd);
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		stfd.setStatus_fd(status_fd);
		session.save(stfd);
		transaction.commit();
		session.close();
		return null;
	}

	public Status_fd UpdateStatusFd(int status_fd_id, String status_fd) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		stfd = stfdrepo.getById(status_fd_id);
		stfd.setStatus_fd(status_fd);
		stfdrepo.save(stfd);
		t.commit();
		session.close();
		return null;
	}

	public Status_fd DeleteStatusFd(int status_fd_id) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		stfd = stfdrepo.getById(status_fd_id);
		stfdrepo.delete(stfd);
		t.commit();
		session.close();
		return null;
	}

	public List<VisaType> getAllVisaType() {
		Session session = sessionFactory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();

		CriteriaQuery<VisaType> cr = cb.createQuery(VisaType.class);
		Root<VisaType> root = cr.from(VisaType.class);

		Query query = session.createQuery(cr);
		List<VisaType> results = query.getResultList();

		session.close();
		return results;
	}

	public VisaType AddVisaType(String visa_type) {
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		vt.setVisa_type(visa_type);
		session.save(vt);
		transaction.commit();
		session.close();
		return null;
	}

	public VisaType UpdateVisaType(int visa_type_id, String visa_type) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		vt = vtrepo.getById(visa_type_id);
		vt.setVisa_type(visa_type);
		vtrepo.save(vt);
		t.commit();
		session.close();
		return null;
	}

	public VisaType DeleteVisaType(int visa_type_id) {
		Session session = sessionFactory.openSession();
		Transaction t = session.beginTransaction();
		vt = vtrepo.getById(visa_type_id);
		vtrepo.delete(vt);
		t.commit();
		session.close();
		return null;
	}

	

}
