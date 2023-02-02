package com.staffing.demo.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.staffing.demo.entity.StatusTbl;

public interface StatusTblRepo extends JpaRepository<StatusTbl, Integer> {

	@Modifying
	@Transactional
	@Query("UPDATE StatusTbl st  SET st.flag = false where requisition_id=:requisiton_id "
			+ "and recruiter_id=:recruiter_id")
	void setEnabledFalse1(int requisiton_id, int recruiter_id);

	@Modifying
	@Transactional
	@Query("UPDATE StatusTbl st  SET st.flag = false where requisition_id=:requisiton_id "
			+ "and recruiter_id=:recruiter_id and candidate_id=:candidate_id")
	void setEnabledFalse2(int recruiter_id,int requisiton_id,  int candidate_id);
}
