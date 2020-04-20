package com.ust.ticl.springboot.pollingsystem.persistence.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ust.ticl.springboot.pollingsystem.persistence.repository.PollingSystemRepository;
import com.ust.ticl.springboot.pollingsystem.web.model.PollingDetail;


@Repository
public class PollingSystemDomainImpl implements PollingSystemDomain{

	@Autowired
	private PollingSystemRepository pollingSystemRepository;
	
	public boolean createPolling(PollingDetail pollingDetail) {
		try {
			pollingSystemRepository.save(pollingDetail);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	

}
