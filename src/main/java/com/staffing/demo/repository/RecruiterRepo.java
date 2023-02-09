package com.staffing.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.staffing.demo.entity.Recruiter;
import com.staffing.demo.entity.Requisition;

public interface RecruiterRepo extends JpaRepository<Recruiter, Integer> {
	
	@Query("select rec from Recruiter rec order by rec.recruiter_id desc")
	@Override
	List<Recruiter> findAll();

}
