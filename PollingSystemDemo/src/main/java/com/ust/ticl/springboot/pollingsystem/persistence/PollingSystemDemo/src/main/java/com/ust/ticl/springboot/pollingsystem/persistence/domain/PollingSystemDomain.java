package com.ust.ticl.springboot.pollingsystem.persistence.domain;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Repository;

import com.ust.ticl.springboot.pollingsystem.web.bo.ActualPollingDetails;
import com.ust.ticl.springboot.pollingsystem.web.model.ActualPolling;
import com.ust.ticl.springboot.pollingsystem.web.model.OptionDetail;
import com.ust.ticl.springboot.pollingsystem.web.model.PollingDetail;
import com.ust.ticl.springboot.pollingsystem.web.model.PollingUser;

@Repository
public interface PollingSystemDomain {
	
	public void createPolling(PollingDetail pollingDetail);

	public PollingDetail getPollingById(Long pollId);

	public List<PollingUser> getUserDetails();

	public List<OptionDetail> getOptionsByoptionId(Iterable<Long> optionId);

	public void deletePollingById(Long pollId);

	public void saveActualPolling(ActualPolling actualPolling);

	public List<ActualPolling> fetchPollingResult(Iterable<Long> pollIds);

	public List<ActualPolling> findAllActualPollings();

}
