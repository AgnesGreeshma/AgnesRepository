package com.ust.ticl.springboot.pollingsystem.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
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
		
		DateFormat dateTimeFormatter=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
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
		if(Objects.nonNull(pollingDetails.getMultiSelect())
				&& !pollingDetails.getMultiSelect().isEmpty()){
			pollingMaster.setMultiSelect(pollingDetails.getMultiSelect());
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
		ActualPolling actualPolling = null;
		StringBuffer actual_pollingId = null;
		String userName = actualPollingDetails.getPollingUser();
		PollingDetail pollingDetail = pollingSystemDomain.getPollingById(Long.parseLong(actualPollingDetails.getPollId()));
		java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());
		if (Objects.nonNull(pollingDetail) 
				&& currentDate.after(pollingDetail.getFromDate())
				&& currentDate.before(pollingDetail.getToDate())) {
			List<PollingUser> pollingUsers = pollingSystemDomain.getUserDetails();
			if(Objects.nonNull(pollingUsers) && !pollingUsers.isEmpty()) {
				for(PollingUser pollingUser : pollingUsers) {
					if(userName.equalsIgnoreCase(pollingUser.getUserName())) {
						boolean alreadyPolled = false;
						String currenttime = currentDate.toLocaleString().replace(" ", "");
						actual_pollingId = new StringBuffer();
						actual_pollingId.append(actualPollingDetails.getPollId())
										.append(pollingUser.getUserId());
						
						List<ActualPolling> actualPollings = pollingSystemDomain.findAllActualPollings();
						if (Objects.nonNull(actualPollings) && !actualPollings.isEmpty()) {
							for (ActualPolling exsistingPolls : actualPollings) {
								String userPollDetails = exsistingPolls.getActualPollId().split("_")[0];
								String pollTime = exsistingPolls.getActualPollId().split("_")[0];
								if(FLAG_YES.equalsIgnoreCase(pollingDetail.getMultiSelect())) {
									if(!pollTime.equals(currenttime)) {
										alreadyPolled = true;
									}
								}else {
									if(actualPollingDetails.getSelectedOption().size()>1) {
										baseResponse.setMessage("Cannot save multiple options");
										break;
									}
									if (userPollDetails.equals(actual_pollingId.toString())) {
										alreadyPolled = true;
									}
								}
								
							}
						}
						actual_pollingId.append("_").append(currenttime);
						if(FLAG_YES.equalsIgnoreCase(pollingUser.getPrivileged()) && !alreadyPolled) {
							List<ActualPolling> actualPollingsToBeSaved = null;
							for(String optionSelected :  actualPollingDetails.getSelectedOption()) {
								actualPolling = new ActualPolling();
								actualPolling.setActualPollId(actual_pollingId.toString());
								actualPolling.setPollId(Long.parseLong(actualPollingDetails.getPollId()));
								actualPolling.setPollName(actualPollingDetails.getPollName());
								actualPolling.setSelectedOption(optionSelected);
								actualPolling.setLastUpdatedUser(pollingUser.getUserName());
								if(Objects.nonNull(actualPollingsToBeSaved) && !actualPollingsToBeSaved.isEmpty()) {
									actualPollingsToBeSaved.add(actualPolling);
								}else {
									actualPollingsToBeSaved = new ArrayList<>();
									actualPollingsToBeSaved.add(actualPolling);
								}
							}
							
							pollingSystemDomain.saveActualPolling(actualPollingsToBeSaved);
							baseResponse.setMessage("Your options are saved successfully");
							break;
						}
						else {
							baseResponse.setMessage("Sorry! The user has already polled/User don't have the privilage to poll");
							break;
						}
					}
				}
			}
		}
		return baseResponse;
	}

	@Override
	public BaseResponse fetchPollingResult(ActualPollingDetails actualPollingDetails) {
		BaseResponse baseResponse = new BaseResponse();;
		String highestPolledOption = "";
		PollingDetail pollingDetail = pollingSystemDomain.getPollingById(Long.parseLong(actualPollingDetails.getPollId()));
		java.sql.Date currentDate = new java.sql.Date(new java.util.Date().getTime());
		if(Objects.nonNull(pollingDetail)){
		if (currentDate.after(pollingDetail.getFromDate())
					&& currentDate.before(pollingDetail.getToDate())) {
				List<ActualPolling> pollingResults = pollingSystemDomain.findAllActualPollings();
				if(Objects.nonNull(pollingResults) && !pollingResults.isEmpty()) {
					HashMap<String,Integer> optionsCountMap = new HashMap<String,Integer>();
					for(ActualPolling pollingResult : pollingResults) {
						if(Long.parseLong(actualPollingDetails.getPollId()) == pollingResult.getPollId()) {
							String selectedOption =  pollingResult.getSelectedOption();
							if(optionsCountMap.keySet().contains(selectedOption)) {
								int optionCount = optionsCountMap.get(selectedOption);
								optionsCountMap.put(selectedOption, ++optionCount);	
							}
							else {
								optionsCountMap.put(selectedOption, 1);
							}
						}
					}
					int maxValueInMap=(Collections.max(optionsCountMap.values()));  // This will return max value in the Hashmap
					for (HashMap.Entry<String, Integer> entry : optionsCountMap.entrySet()) {
						if (entry.getValue() == maxValueInMap) {
							highestPolledOption = entry.getKey();
						}
					}
					
					baseResponse.setMessage("Winner is >> "+highestPolledOption+" <<");
				}
			}else {
				baseResponse.setMessage("No results found!  Poll expired");
			}
		}else {
			baseResponse.setMessage("No results found! Invalid poll Id");
		}
		return baseResponse;
	}
}
