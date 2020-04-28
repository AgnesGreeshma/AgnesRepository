package com.ust.ticl.springboot.pollingsystem.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.ticl.springboot.pollingsystem.web.model.PollingUser;

@Repository
public interface PollingUserRepository extends JpaRepository<PollingUser, Long>{

}
