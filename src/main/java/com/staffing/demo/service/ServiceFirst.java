package com.staffing.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.staffing.demo.entity.Candidate;
import com.staffing.demo.entity.Recruiter;
import com.staffing.demo.entity.Requisition;
import com.staffing.demo.entity.StatusTbl;
import com.staffing.demo.repository.RequisitionRepo;
import com.staffing.demo.repository.StatusTblRepo;

@Service
public class ServiceFirst {

	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	RequisitionRepo requisitionRepo;

	@Autowired
	StatusTblRepo strepo;

	@Autowired
	ServiceFirst serviceFirst;

	Recruiter rec = new Recruiter();
	Requisition rq = new Requisition();
	StatusTbl st = new StatusTbl();
	Candidate cd = new Candidate();

	LocalDate now = LocalDate.now();
	DateTimeFormatter dtf_month = DateTimeFormatter.ofPattern("MMM-yyyy");

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

	public ResponseEntity<?> AddRecruiter(String recruiter_name, String recruiter_email, String role, String password) {

		System.out.println(
				"Value reach to Service" + recruiter_name + "  " + recruiter_email + " " + role + " " + password);

		Session hbmsession = null;
		Transaction transaction = null;

		rec.setRecruiter_name(recruiter_name);
		rec.setRecruiter_email(recruiter_email);
		rec.setPassword(password);
		rec.setRole(role);

		hbmsession = sessionFactory.openSession();
		transaction = hbmsession.beginTransaction();
		hbmsession.save(rec);
		transaction.commit();
		hbmsession.close();

		return new ResponseEntity<Recruiter>(rec, HttpStatus.OK);
	}

	public ResponseEntity<?> AddRequisition(String requisition_from, Integer id, String client, String job_title,
			String duration, String client_rate, String location, String position_type, String skills,
			int recruiter_id) {

		Session hbmsession = null;
		Transaction transaction = null;

		rq.setRequisition_from(requisition_from);
		rq.setId(id);
		rq.setClient(client);
		rq.setJob_title(job_title);
		rq.setDuration(duration);
		rq.setClient_rate(client_rate);
		rq.setLocation(location);
		rq.setPosition_type(position_type);
		rq.setSkills(skills);

		hbmsession = sessionFactory.openSession();
		transaction = hbmsession.beginTransaction();

		int rq2 = (Integer) hbmsession.save(rq);

		st.setStatus("Assigned");
		st.setStatus_date(now);
		rec.setRecruiter_id(recruiter_id);
		st.setRecruiter(rec);
		rq.setRequisition_id(rq2);
		st.setRequisition(rq);

		hbmsession.save(st);

		transaction.commit();
		hbmsession.close();

		return new ResponseEntity<Requisition>(rq, HttpStatus.OK);
	}

	public ResponseEntity<?> AddCandidate(String candidate_name, String visa_type, String rate_term,
			String submitted_rate, String pnone, String email, String remark, String reason, int recruiter_id,
			int requisition_id) {

		Session hbmsession = null;
		Transaction transaction = null;

		cd.setCandidate_name(candidate_name);
		cd.setVisa_type(visa_type);
		cd.setRate_term(rate_term);
		cd.setSubmitted_rate(submitted_rate);
		cd.setPnone(pnone);
		cd.setEmail(email);
		cd.setRemark(remark);
		cd.setReason(reason);

		hbmsession = sessionFactory.openSession();
		transaction = hbmsession.beginTransaction();
		int candi2 = (Integer) hbmsession.save(cd);

		st.setStatus("Candidate Screening");
		st.setStatus_date(now);
		rec.setRecruiter_id(recruiter_id);
		st.setRecruiter(rec);
		rq.setRequisition_id(requisition_id);
		st.setRequisition(rq);

		cd.setCandidate_id(candi2);
		st.setCandidate(cd);

		hbmsession.save(st);

		transaction.commit();
		hbmsession.close();

		return new ResponseEntity<Candidate>(cd, HttpStatus.OK);
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
//				System.out.println("Status : " + x.getStatus_id());			
				st.setStatus_id(sid);
				st.setFlag(false);
				session.saveOrUpdate(st);
				session.flush();
				session.clear();
			}

			System.out.println("Profile updated successful!");
		}
		session.close();
		return results;
	}

	public ResponseEntity<?> AddStatus1(int recruiter_id, int requisition_id, String status) {
	
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		st.setStatus(status);
		st.setStatus_date(now);
		rec.setRecruiter_id(recruiter_id);
		st.setRecruiter(rec);
		rq.setRequisition_id(requisition_id);
		st.setRequisition(rq);
		st.setFlag(true);
		
		session.save(st);
		System.out.println("Status without candidate updated successful!");

		transaction.commit();
		session.close();
		return new ResponseEntity<StatusTbl>(st, HttpStatus.OK);
	}

	public ResponseEntity<?> AddStatus2(int recruiter_id, int requisition_id, int candidate_id, String status) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		st.setStatus(status);
		st.setStatus_date(now);
		rec.setRecruiter_id(recruiter_id);
		st.setRecruiter(rec);
		rq.setRequisition_id(requisition_id);
		st.setRequisition(rq);
		cd.setCandidate_id(candidate_id);
		st.setCandidate(cd);
		st.setFlag(true);

		System.out.println("Status with Candidate updated successful!");
		session.save(st);

		transaction.commit();
		session.close();
		return new ResponseEntity<StatusTbl>(st, HttpStatus.OK);
	}

	public List<StatusTbl> getLastStatus1(int recruiter_id, int requisition_id, String status) {
		strepo.setEnabledFalse1(recruiter_id, requisition_id);
		
		System.out.println("H1");
		AddStatus1(recruiter_id, requisition_id, status);
		return null;
	}

	public List<StatusTbl> getLastStatus2(int recruiter_id, int requisition_id,  int candidate_id, String status) {
		strepo.setEnabledFalse2(recruiter_id, requisition_id, candidate_id);

		AddStatus2(recruiter_id, requisition_id, candidate_id, status);
		return null;
	}

}
