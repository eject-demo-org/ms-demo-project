package com.demo.appservice.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "M_APPLICATION")
public class Application{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "APPLICATION_ID")
	private Long applicationId;
	@Column(name = "APPLICATION_NUMBER")
	private Long applicationNumber;
	
	@OneToMany(mappedBy = "application" , cascade = CascadeType.ALL)
	List<ApplicantDetails> applicantDetails;


}
