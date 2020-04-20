package com.ust.ticl.springboot.pollingsystem.web.model;

import java.util.Date;
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

@Entity 
@Table(name="POLLING_MASTER")

@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class PollingDetail {
	
	@Id
	@Column(name="PollingID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int pollId;
	
	@Column(name="PollingName")
	private String pollName;
	
	@Column(name="PollType")
	private String pollType;
	
	@Column(name="OptionType")
	private String optionType;
	
	@Temporal(TemporalType.DATE)
	@Column(name="CreatedDate")
	private Date createdDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="FromDate")
	private Date fromDate;
	
	@Temporal(TemporalType.DATE)
	@Column(name="ToDate")
	private Date toDate;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "UserID", referencedColumnName = "UserID")
	private PollingUser pollingUser;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "PollingID", referencedColumnName = "PollingID")
	private Set<OptionDetail> options;

	
}
