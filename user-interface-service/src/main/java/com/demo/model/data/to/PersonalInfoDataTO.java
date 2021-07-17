package com.demo.model.data.to;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;

@Data
public class PersonalInfoDataTO {
	
	private String uid;
	@JsonAlias("firstName")
	private String fName;
	@JsonAlias("lastName")
	private String lName;
	private Date dob;
	private Integer maritalCode;
	private Integer birthPlaceCode;
	private Integer genderCode;
	private Integer citizenshipCode;
	
	
	
	
	
	
	
}
