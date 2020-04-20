package com.ust.ticl.springboot.pollingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.ticl.springboot.pollingsystem.persistence.domain.PollingSystemDomain;
import com.ust.ticl.springboot.pollingsystem.web.bo.PollingDetails;
import com.ust.ticl.springboot.pollingsystem.web.model.BaseResponse;
import com.ust.ticl.springboot.pollingsystem.web.model.PollingDetail;


@Service
public class PollingSystemServiceImpl implements PollingSystemService  {
	
	@Autowired
	private PollingSystemDomain pollingSystemDomain;
	
	public BaseResponse createPolling(PollingDetails pollingDetails) {
		BaseResponse response = new BaseResponse();
		PollingDetail pollingMaster = new PollingDetail();
		pollingMaster.setPollName(pollingDetails.getPollName());
		pollingMaster.setPollType(pollingDetails.getPollType());
		pollingMaster.setOptionType(pollingDetails.getOptionType());
		//pollingMaster.setoptions
		//pollingMaster.setFromDate(pollingDetails.get);
		//pollingMaster.setToDate(pollingDetails.get);
		pollingSystemDomain.createPolling(pollingMaster);
		return response;
	}
}
