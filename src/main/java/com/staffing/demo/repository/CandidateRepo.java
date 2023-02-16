package com.staffing.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staffing.demo.entity.Candidate;


public interface CandidateRepo  extends JpaRepository<Candidate, Integer> {

}
