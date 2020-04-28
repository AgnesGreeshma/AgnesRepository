package com.ust.ticl.springboot.pollingsystem.web.bo;

import java.util.Set;

import com.ust.ticl.springboot.pollingsystem.web.model.BaseRequest;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter@Setter
public class PollingDetails extends BaseRequest { 

	private String pollId;
	private String pollName;
	private String pollType;
	private String optionType;
	private String multiSelect;
	private Set<OptionDetails> options;
	private String fromDate;
	private String toDate;
	private String pollingUser;

}
