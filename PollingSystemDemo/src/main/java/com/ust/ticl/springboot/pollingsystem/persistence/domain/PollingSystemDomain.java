package com.ust.ticl.springboot.pollingsystem.persistence.domain;

import org.springframework.stereotype.Repository;

import com.ust.ticl.springboot.pollingsystem.web.model.PollingDetail;

@Repository
public interface PollingSystemDomain {
	public boolean createPolling(PollingDetail pollingDetail);

}
