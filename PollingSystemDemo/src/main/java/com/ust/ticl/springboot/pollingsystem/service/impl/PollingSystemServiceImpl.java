package com.ust.ticl.springboot.pollingsystem.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ust.ticl.springboot.pollingsystem.persistence.domain.PollingSystemDomain;
import com.ust.ticl.springboot.pollingsystem.service.PollingSystemService;
import com.ust.ticl.springboot.pollingsystem.web.bo.ActualPollingDetails;
import com.ust.ticl.springboot.pollingsystem.web.bo.OptionDetails;
import com.ust.ticl.springboot.pollingsystem.web.bo.PollingDetails;
import com.ust.ticl.springboot.pollingsystem.web.model.ActualPolling;
import com.ust.ticl.springboot.pollingsystem.web.model.BaseResponse;
import com.ust.ticl.springboot.pollingsystem.web.model.OptionDetail;
import com.ust.ticl.springboot.pollingsystem.web.model.PollingDetail;
import com.ust.ticl.springboot.pollingsystem.web.model.PollingUser;


@Service
public class PollingSystemServiceImpl implements PollingSystemService  {
	
	@Autowired
	private PollingSystemDomain pollingSystemDomain;
	
	private static final String FLAG_YES = "Y";
//	private Log log;
	
	@Override
	public BaseResponse createPolling(PollingDetails pollingDetails) {
		
	//	log.info("Entered PollingSystemServiceImpl.createPolling()");
		
		BaseResponse response = new BaseResponse();
		
		PollingDetail pollingMaster = null;
		String userName = null;
		
		if(Objects.nonNull(pollingDetails)) {
			
			userName = pollingDetails.getPollingUser();
			List<PollingUser> pollingUsers = pollingSystemDomain.getUserDetails();
			
			if(Objects.nonNull(pollingUsers) && !pollingUsers.isEmpty()) {
				for(PollingUser pollingUser : pollingUsers) {
					if(userName.equalsIgnoreCase(pollingUser.getUserName())) {
						if(FLAG_YES.equalsIgnoreCase(pollingUser.getAdmin())) {
							if(Objects.isNull(pollingDetails.getPollId()) 
									|| pollingDetails.getPollId().isEmpty()){
									pollingMaster = new PollingDetail();
							} else {
								pollingMaster = pollingSystemDomain.getPollingById(Long.parseLong(pollingDetails.getPollId()));
							}
							
							pollingMaster = updatePollingMasterDetails(pollingDetails, pollingMaster, pollingUser);
							pollingSystemDomain.createPolling(pollingMaster);
							response.setMessage("Polling created successfully");
						}
					}
				}
				
			}
			else {
				response.setMessage("No such user exists");
			}
		}
		else {
			response.setMessage("PollingDetails is Empty");
		}
		
	//	log.info("Exited PollingSystemServiceImpl.createPolling()");
		
		return response;
	}

	private PollingDetail updatePollingMasterDetails(PollingDetails pollingDetails,
			PollingDetail pollingMaster, PollingUser pollingUser) {
		
		DateFormat dateTimeFormatter=new SimpleDateFormat("yyyy-MM-DD HH:mm:ss");  
		OptionDetail optionDetail = null;
		
		if(Objects.nonNull(pollingDetails.getPollName())
				&& !pollingDetails.getPollName().isEmpty()){
			pollingMaster.setPollName(pollingDetails.getPollName());
		}
		if(Objects.nonNull(pollingDetails.getPollType())
				&& !pollingDetails.getPollType().isEmpty()){
			pollingMaster.setPollType(pollingDetails.getPollType());
		}
		if(Objects.nonNull(pollingDetails.getOptionType())
				&& !pollingDetails.getOptionType().isEmpty()){
			pollingMaster.setOptionType(pollingDetails.getOptionType());
		}
	
		updateOptionDetail(pollingMaster,optionDetail,pollingUser, pollingDetails);
		
		
		try {
			if (Objects.nonNull(pollingDetails.getFromDate()) 
					&& pollingDetails.getFromDate().trim().length() > 0) {
				Date fromDate = dateTimeFormatter.parse(pollingDetails.getFromDate());
				pollingMaster.setFromDate(new java.sql.Date(fromDate.getTime()));
			}
			if (Objects.nonNull(pollingDetails.getToDate()) 
					&& pollingDetails.getToDate().trim().length() > 0) {
				Date toDate =  dateTimeFormatter.parse(pollingDetails.getToDate());
				pollingMaster.setToDate(new java.sql.Date(toDate.getTime()));
			}
		} catch (ParseException e) {
			//log.info("ParseException caught");
		}
		
		pollingMaster.setPollingUser(pollingUser.getUserId());
		return pollingMaster;
	}

	private void updateOptionDetail(PollingDetail pollingMaster, OptionDetail optionDetail,
			PollingUser pollingUser,PollingDetails pollingDetails) {
		if(Objects.nonNull(pollingDetails.getOptions())
				&& !pollingDetails.getOptions().isEmpty()) { 
			
			for(OptionDetails optionDetails : pollingDetails.getOptions()) {
				if(Objects.isNull(pollingDetails.getPollId()) 
						|| pollingDetails.getPollId().isEmpty()){
					optionDetail = new OptionDetail();
				}else {
					List<Long> pollId = new ArrayList<Long>();
					pollId.add(Long.parseLong(pollingDetails.getPollId()));
					Set<OptionDetail> options= pollingMaster.getOptions();
					Set<OptionDetail> updatedOptions = new HashSet<OptionDetail>();
					if(Objects.nonNull(options) && !options.isEmpty()){
						for(OptionDetail option : options) {
							if(Integer.parseInt(optionDetails.getOptionId()) == option.getOptionId()) {
								optionDetail = option;
							}else {
								updatedOptions.add(option);
							}
						}
					}
					pollingMaster.setOptions(updatedOptions);
				}
				if(Objects.nonNull(optionDetails.getOptionName())
						&& !optionDetails.getOptionName().isEmpty()) {
					optionDetail.setOptionName(optionDetails.getOptionName());
				}
				if(Objects.nonNull(optionDetails.getOptionValue())
						&& !optionDetails.getOptionValue().isEmpty()) {
					optionDetail.setOptionValue(optionDetails.getOptionValue());
				}
				if(Objects.nonNull(optionDetails.getValueType())
						&& !optionDetails.getValueType().isEmpty()) {
					optionDetail.setValueType(optionDetails.getValueType());
				}
				
				optionDetail.setPollingUser(pollingUser.getUserId());
				
				if(Objects.nonNull(pollingMaster.getOptions())
						&& !pollingMaster.getOptions().isEmpty()) {
					 pollingMaster.getOptions().add(optionDetail);
				} else {
					 pollingMaster.setOptions(new HashSet<OptionDetail>());
					 pollingMaster.getOptions().add(optionDetail);
				}
				
			}
		}
	}

	@Override
	public PollingDetail getPollingById(String pollId) {
		PollingDetail pollingMaster = pollingSystemDomain.getPollingById(Long.parseLong(pollId));
		return pollingMaster;
	}

	@Override
	public BaseResponse deletePollingById(String pollId) {
		BaseResponse response = new BaseResponse();
		pollingSystemDomain.deletePollingById(Long.parseLong(pollId));
		response.setMessage("Polling deleted succesfully");
		return response;
	}

	@Override
	public BaseResponse saveActualPolling(ActualPollingDetails actualPollingDetails) {
		BaseResponse baseResponse = new BaseResponse();
		ActualPolling actualPolling = new ActualPolling();
		
		//add the logic here
		
		
		
		
		
		
		pollingSystemDomain.saveActualPolling(actualPolling);
		return baseResponse;
	}

	@Override
	public ActualPollingDetails fetchPollingResult(String pollId) {
		List<Long> pollIds = new ArrayList<>();
		ActualPollingDetails actualPollingDetails = null;
		pollIds.add(Long.parseLong(pollId));
		List<ActualPolling> actualPollings = pollingSystemDomain.fetchPollingResult(pollIds);
		if(Objects.nonNull(actualPollings) && !actualPollings.isEmpty()) {
			//add the logic here
			
		}
		return actualPollingDetails;
	}
}
