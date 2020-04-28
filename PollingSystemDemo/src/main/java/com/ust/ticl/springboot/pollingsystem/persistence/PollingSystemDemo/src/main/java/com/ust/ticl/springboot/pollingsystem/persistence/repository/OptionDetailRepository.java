package com.ust.ticl.springboot.pollingsystem.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ust.ticl.springboot.pollingsystem.web.model.OptionDetail;

@Repository
public interface OptionDetailRepository extends JpaRepository<OptionDetail, Long>{

}