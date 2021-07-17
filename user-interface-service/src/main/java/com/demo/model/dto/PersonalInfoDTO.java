package com.demo.model.dto;


import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.demo.model.data.base.to.PersonalInfoDataBaseTO;
import com.demo.model.data.to.AddressDataTO;
import com.demo.model.data.to.PersonalInfoDataTO;
import com.demo.model.mapper.AddressMapper;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Document(collection = "personalInfo")
@Getter
@Setter
@ToString(includeFieldNames = true)
@NoArgsConstructor
public class PersonalInfoDTO {

	@Id
	private UUID id;

	private String uniqueId;
	private String firstName;
	private String lastName;
	private Date birthDate;
	private Integer birthPlace;	
	private Integer citizenship;
	private Integer gender;
	private Integer maritalStatus;

	private Set<AddressDTO> addresses;



}
