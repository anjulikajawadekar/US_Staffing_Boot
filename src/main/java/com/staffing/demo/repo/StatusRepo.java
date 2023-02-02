package com.staffing.demo.repo;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.staffing.demo.entity.Status;

public interface StatusRepo extends JpaRepository<Status, Integer>{
	  @Modifying
	    @Transactional
	    @Query("UPDATE Status st  SET st.flag = false where requisition_id=:requisiton_id "
	    		+ "and recruiter_id=:recruiter_id")
	    void setEnabledFalse1(int requisiton_id ,int recruiter_id);
	  
	  @Modifying
	    @Transactional
	    @Query("UPDATE Status st  SET st.flag = false where requisition_id=:requisiton_id "
	    		+ "and recruiter_id=:recruiter_id and candidate_id=:candidate_id")
	    void setEnabledFalse2(int requisiton_id ,int recruiter_id,int candidate_id);
	 
}
