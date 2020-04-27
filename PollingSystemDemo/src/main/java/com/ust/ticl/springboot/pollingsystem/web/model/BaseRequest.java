package com.ust.ticl.springboot.pollingsystem.web.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BaseRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String appName = "Polling System App";
	
	private String version = "Version 1.0";

}
