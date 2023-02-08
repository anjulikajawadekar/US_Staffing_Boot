package com.staffing.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staffing.demo.entity_fd.Duration;

public interface DurationRepo  extends JpaRepository<Duration, Integer> {

}
