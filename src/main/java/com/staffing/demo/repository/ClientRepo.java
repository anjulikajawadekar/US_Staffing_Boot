package com.staffing.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.staffing.demo.entity_fd.Client;

public interface ClientRepo  extends JpaRepository<Client, Integer> {

}
