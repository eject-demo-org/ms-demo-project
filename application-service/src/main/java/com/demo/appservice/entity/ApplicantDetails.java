package com.demo.appservice.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "M_APPLICANT")
public class ApplicantDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APPLICANT_ID")
	private Long applicationId;
	@Column(name = "CUIID")
	private String cuiid;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "APPLICATION_ID")
	private Application application;
	private String applicantType;
	
	
	
}
