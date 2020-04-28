package com.ust.ticl.springboot.pollingsystem.web.model;

import java.sql.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author : Agnes
 * @use    : Polling Master 
 *
 */
@Component
@Entity 
@Table(name="POLLING_MASTER")

@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class PollingDetail {
	
	@Id
	@Column(name="PollingID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long pollId;
	
	@Column(name="PollingName")
	private String pollName;
	
	@Column(name="PollType")
	private String pollType;
	
	@Column(name="OptionType")
	private String optionType;
	
	@Column(name="isMultiSelect")
	private String multiSelect;

	@Column(name="LastUpdatedTime")
	@CreationTimestamp
	private Date lastUpdatedTime;
	
	@Column(name="FromDate")
	private Date fromDate;
	
	
	@Column(name="ToDate")
	private Date toDate;

	@Column(name="UserID")
	private Long pollingUser;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "PollingID", referencedColumnName = "PollingID")
	private Set<OptionDetail> options;

	
}
