package com.staffing.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staffing.demo.entity_fd.VisaType;

public interface VisaTypeRepo  extends JpaRepository<VisaType, Integer> {

}
