package com.ust.ticl.springboot.pollingsystem.web.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BaseResponse implements Serializable {

	private String appName = "Polling System App";

	private String version = "Version 1.0";
	
	private String message;

}
