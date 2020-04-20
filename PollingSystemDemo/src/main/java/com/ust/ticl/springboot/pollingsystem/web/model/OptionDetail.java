package com.ust.ticl.springboot.pollingsystem.web.model;


import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name="POLLING_OPTION")

@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class OptionDetail {
	
	@Id
	@Column(name="OptionId")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int optionId;
	
	@Column(name="OptionName")
	private String optionName;
	
	@Column(name="OptionValue")
	private String optionValue;
	
	@Column(name="ValueType")
	private String valueType;
	
	@Column(name="PollingID")
	private int pollingID;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
	private PollingUser pollingUser;
	
	@Temporal(TemporalType.DATE)
	@Column(name="CreatedTime")
	private Date createdTime;

	
}
