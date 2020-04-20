package com.ust.ticl.springboot.pollingsystem.web.bo;

import java.util.List;

import com.ust.ticl.springboot.pollingsystem.web.model.BaseRequest;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@NoArgsConstructor
@Getter
@Setter
public class PollingDetails extends BaseRequest { 

	private String pollId;
	private String pollName;
	private String pollType;
	private String optionType;
	private List<OptionDetails> options;

}
