package com.test.models;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestModel {
	
	private String name;
	private Date dob;
	private BigDecimal salary;
	
	

}
