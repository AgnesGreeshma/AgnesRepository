package com.ust.ticl.springboot.pollingsystem.web.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Component
@Entity
@Getter @Setter @NoArgsConstructor
@Table(name="ACTUAL_POLLING")
public class ActualPolling {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@Column(name="actual_Polling_Id")
	private String actualPollId;

	@Column(name="PollingID")
	private Long pollId;
	
	@Column(name="PollingName")
	private String pollName;
	
	@Column(name="lastUpdatedTime")
	private Date lastUpdatedTime;
	
	@Column(name="lastUpdatedUser")
	private String lastUpdatedUser;
	
	@Column(name="SelectedOption")
	private String selectedOption;
		
}
