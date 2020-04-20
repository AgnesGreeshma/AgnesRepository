package com.ust.ticl.springboot.pollingsystem.web.bo;

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
public class OptionDetails extends BaseRequest { 
	private String optionId;
	private String optionName;
	private String optionValue;
}
