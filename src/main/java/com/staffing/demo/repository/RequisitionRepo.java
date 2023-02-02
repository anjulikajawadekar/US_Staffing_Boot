package com.staffing.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staffing.demo.entity.Requisition;

public interface RequisitionRepo extends JpaRepository<Requisition, Integer> {

}
