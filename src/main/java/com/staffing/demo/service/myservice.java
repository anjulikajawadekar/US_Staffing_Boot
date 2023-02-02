package com.staffing.demo.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.staffing.demo.entity.Candidate;
import com.staffing.demo.entity.Recruiter;
import com.staffing.demo.entity.Requisition;
import com.staffing.demo.entity.Status;
import com.staffing.demo.repo.RecruiterRepo;
import com.staffing.demo.repo.RequisitionRepo;
import com.staffing.demo.repo.StatusRepo;

@Service
public class myservice {
	@Autowired
	SessionFactory factory = null;
	
	@Autowired
	private RequisitionRepo reqrepo;

	@Autowired
	private RecruiterRepo recrepo;
	
	@Autowired
	private StatusRepo strepo;

	LocalDate now = LocalDate.now();
	Requisition req = new Requisition();
	Recruiter rec = new Recruiter();
	Candidate can = new Candidate();
	Status st = new Status();

	public String addReq(int recruiter_id, String requisition_from, int id, String client, String job_title,
			String duration, String client_rate, String location, String position_type, String skill) {
		
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		
		rec.setRecruiter_id(recruiter_id);
		
		req.setRequisition_from(requisition_from);
		req.setId(id);
		req.setClient(client);
		req.setJob_title(job_title);
		req.setDuration(duration);
		req.setClient_rate(client_rate);
		req.setLocation(location);
		req.setPosition_type(position_type);
		req.setSkills(skill);
		req.setCurrent_status("Assigned");
		req.setRecruiter(rec);
		
		
		st.setStatus("Assigned");
		st.setStatus_date(now);
		st.setRequisition(req);
		st.setRecruiter(rec);
		
		session.save(req);
		session.save(st);
		t.commit();
		session.close();
		return "Requisition added";
	}

	public String addcandidate(int recruiter_id, int requisiton_id, String candidate_name, String visa_type,
			String rate_term, String submitted_rate, String phone,String email,String status, String remark, String reason) {

		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		rec.setRecruiter_id(recruiter_id);
		req.setRequisition_id(requisiton_id);
		
		
		can.setCandidate_name(candidate_name);
		can.setVisa_type(visa_type);
		can.setRate_term(rate_term);
		can.setSubmitted_rate(submitted_rate);;
		can.setPnone(phone);
		can.setEmail(email);
		can.setRemark(remark);
		can.setReason(reason);
		
		st.setStatus(status);
		st.setCandidate(can);
		st.setRequisition(req);
		st.setStatus_date(now);
		
		
		can.setRequisition(req);
		can.setRecruiter(rec);
		session.save(can);
		session.save(st);
		t.commit();
		session.close();
		return "candidate added";
	}

	public String addStatus(int requisiton_id,int recruiter_id, int canid, String status) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		req.setRequisition_id(requisiton_id);
		rec.setRecruiter_id(recruiter_id);
		can.setCandidate_id(canid);
		
		st.setStatus(status);
		st.setCandidate(can);
		st.setStatus_date(now);
		st.setRequisition(req);
		st.setRecruiter(rec);
		session.save(st);
		t.commit();
		session.close();
		System.out.println("success status");

		return "status added";
	}

	public List<Status> getStatus() {
		Session session = factory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Status> cr = cb.createQuery(Status.class);
		Root<Status> root = cr.from(Status.class);
		cr.select(root).where(cb.equal(root.get("requisition").get("requisition_id"), 2));
		Query query = session.createQuery(cr);
		List<Status> results = query.getResultList();
		session.close();

		return results;
	}
	
	public List<Status> getLastStatus() {
		String date1="2023-02-01";
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate dt1 = LocalDate.parse(date1, formatter);
		Session session = factory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Status> cr = cb.createQuery(Status.class);
		Root<Status> root = cr.from(Status.class);
		cr.select(root).where(cb.equal(root.get("candidate").get("candidate_id"),2),
				(cb.equal(root.get("status_date"),dt1)));
		Query query = session.createQuery(cr);
		List<Status> results = query.getResultList();
		
		session.close();

		return results;
		
		
		 /*CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
	        CriteriaQuery<Status> criteriaQuery = criteriaBuilder.createQuery(Status.class);
	        Root<Status> root = criteriaQuery.from(Status.class);
	        criteriaQuery
	                .select(root)
	                .orderBy(criteriaBuilder.desc(root.get("status")));
	        TypedQuery<Status> findAllSizes = session.createQuery(criteriaQuery);
	        return findAllSizes.getResultStream().findFirst().orElse(null);*/
	}
	public List<Status> getLastStatus1(int requisiton_id, int recruiter_id) {
		
			strepo.setEnabledFalse1(requisiton_id,recruiter_id);
		
		
		
		
		return null;
		
		/*Session session = factory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Status> cr = cb.createQuery(Status.class);
		Root<Status> root = cr.from(Status.class);
		cr.select(root).where(cb.equal(root.get("requisition").get("requisition_id"),7),
				(cb.equal(root.get("flag"),1)));
		Query query = session.createQuery(cr);
	
		
		List<Status> results = query.getResultList();
	results.replaceAll(null);
		/*req=reqrepo.getById(7);
		st.setRequisition(req);
		st.setFlag(false);
		session.saveOrUpdate(st);
		session.close();

		return "updated";
		
		return results;*/
		
		
		
	}
	public List<Status> getLastStatus2(int requisiton_id, int recruiter_id,int canid) {
	
	
		strepo.setEnabledFalse2(requisiton_id,recruiter_id,canid);
	
	return null;
}
	
	
	
	public List<Candidate> getcan() {
		Session session = factory.openSession();
		CriteriaBuilder cb = session.getCriteriaBuilder();
		CriteriaQuery<Candidate> cr = cb.createQuery(Candidate.class);
		Root<Candidate> root = cr.from(Candidate.class);
		cr.select(root);
		Query query = session.createQuery(cr);
		List<Candidate> results = query.getResultList();
		session.close();

		return results;
	}
	
	public String UpdateStatus(int requisiton_id,int canid, String status) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		req=reqrepo.getById(requisiton_id);
		
		req.setCurrent_status(status);
		req.setRequisition_id(requisiton_id);
		can.setCandidate_id(canid);
		st.setStatus(status);
		st.setRequisition(req);
		st.setCandidate(can);
		reqrepo.save(req);
		session.save(st);
		t.commit();
		session.close();

		return "status updated";
	}

	public String UpdateStatus(int status_id) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();

		st=strepo.getById(status_id);
		st.setFlag(true);
		strepo.save(st);
		t.commit();
		session.close();
		return "flag updated";
	}

	


}
