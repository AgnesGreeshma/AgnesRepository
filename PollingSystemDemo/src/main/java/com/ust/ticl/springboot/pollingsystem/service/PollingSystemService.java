package com.ust.ticl.springboot.pollingsystem.service;

import org.springframework.stereotype.Service;

import com.ust.ticl.springboot.pollingsystem.web.bo.PollingDetails;
import com.ust.ticl.springboot.pollingsystem.web.model.BaseResponse;
import com.ust.ticl.springboot.pollingsystem.web.model.PollingDetail;

@Service
public interface PollingSystemService {
	public BaseResponse createPolling(PollingDetails pollingDetails);

}
