package com.ust.ticl.springboot.pollingsystem.web.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Component
@Entity
@Table(name="POLLING_USER")

@Getter @Setter @NoArgsConstructor @ToString @EqualsAndHashCode
public class PollingUser {
	
	@Id
	@Column(name="UserID")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long userId;
	
	@Column(name="UserName")
	private String userName;
	
	@Column(name="IsAdmin")
	private String admin;
	
	@Column(name="isPrivileged")
	private String privileged;

}
