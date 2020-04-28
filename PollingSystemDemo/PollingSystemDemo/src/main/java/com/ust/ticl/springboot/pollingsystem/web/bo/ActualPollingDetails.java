package com.ust.ticl.springboot.pollingsystem.web.bo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class ActualPollingDetails {
	
	
	private String pollId;
	
	private String pollName;
	
	private String pollingUser;

	private List<String> selectedOption;
	
	private String winner;

}
