package com.staffing.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staffing.demo.entity.Recruiter;


public interface RecruiterRepo extends JpaRepository<Recruiter, Integer>{
}
