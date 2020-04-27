package com.ust.ticl.springboot.pollingsystem.persistence.domain.impl;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ust.ticl.springboot.pollingsystem.persistence.domain.PollingSystemDomain;
import com.ust.ticl.springboot.pollingsystem.persistence.repository.ActualPollingRepository;
import com.ust.ticl.springboot.pollingsystem.persistence.repository.OptionDetailRepository;
import com.ust.ticl.springboot.pollingsystem.persistence.repository.PollingDetailRepository;
import com.ust.ticl.springboot.pollingsystem.persistence.repository.PollingUserRepository;
import com.ust.ticl.springboot.pollingsystem.web.bo.ActualPollingDetails;
import com.ust.ticl.springboot.pollingsystem.web.model.ActualPolling;
import com.ust.ticl.springboot.pollingsystem.web.model.OptionDetail;
import com.ust.ticl.springboot.pollingsystem.web.model.PollingDetail;
import com.ust.ticl.springboot.pollingsystem.web.model.PollingUser;

@Repository
public class PollingSystemDomainImpl implements PollingSystemDomain{

	@Autowired
	private PollingDetailRepository pollingDetailRepository;
	
	@Autowired
	private PollingUserRepository pollingUserRepository;

	@Autowired
	private OptionDetailRepository optionDetailRepository;
	
	@Autowired
	private ActualPollingRepository actualPollingRepository;
	
	@Override
	public void createPolling(PollingDetail pollingDetail) {
		
		pollingDetailRepository.save(pollingDetail);
		
	}

	@Override
	public PollingDetail getPollingById(Long pollId) {
		return pollingDetailRepository.findById(pollId).get();
	}

	@Override
	public List<PollingUser> getUserDetails() {
		return 	pollingUserRepository.findAll();
	}

	@Override
	public List<OptionDetail> getOptionsByoptionId(Iterable<Long> optionId) {
		
		return optionDetailRepository.findAllById(optionId);
	}

	@Override
	public void deletePollingById(Long pollId) {
		 pollingDetailRepository.deleteById(pollId);
	}

	@Override
	public void saveActualPolling(ActualPolling actualPolling) {
		actualPollingRepository.save(actualPolling);
	}

	@Override
	public List<ActualPolling> fetchPollingResult(Iterable<Long> pollIds) {
		// TODO Auto-generated method stub
		return actualPollingRepository.findAllById(pollIds);
	}

	
	

}
