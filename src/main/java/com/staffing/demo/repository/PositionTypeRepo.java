package com.staffing.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staffing.demo.entity_fd.PositionType;

public interface PositionTypeRepo  extends JpaRepository<PositionType, Integer> {

}
