package com.ust.ticl.springboot.pollingsystem.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.ticl.springboot.pollingsystem.web.model.ActualPolling;

@Repository
public interface ActualPollingRepository extends JpaRepository<ActualPolling, Long>{

}
