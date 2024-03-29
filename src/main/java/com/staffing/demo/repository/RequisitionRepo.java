package com.staffing.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.staffing.demo.entity.Requisition;
@Transactional
public interface RequisitionRepo extends JpaRepository<Requisition, Integer> {

	@Query("select rq from Requisition rq order by rq.requisition_id desc")
	@Override
	List<Requisition> findAll();

}
