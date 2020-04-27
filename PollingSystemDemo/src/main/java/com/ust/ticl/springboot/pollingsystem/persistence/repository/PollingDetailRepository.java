package com.ust.ticl.springboot.pollingsystem.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ust.ticl.springboot.pollingsystem.web.model.PollingDetail;

@Repository
public interface PollingDetailRepository extends JpaRepository<PollingDetail, Long>{

}
