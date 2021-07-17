package com.demo.model.to;

import java.util.Date;
import java.util.Set;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class PersonalInfoTO implements InterfaceResponseTO{
	
	private String uniqueId;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Integer birthPlaceCode;	
	private Integer citizenshipCode;
	private Integer genderCode;
	private Integer maritalStatusCode;
	
	private Set<AddressTO> addresses;
	

}
