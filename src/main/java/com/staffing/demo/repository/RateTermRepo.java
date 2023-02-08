package com.staffing.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staffing.demo.entity_fd.RateTerm;

public interface RateTermRepo  extends JpaRepository<RateTerm, Integer> {

}
