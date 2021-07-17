package com.demo.model.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class FamilyInfoDetailsDTO {
	
	private String relationId;
	private String id;
	private Integer relation;
	private Integer gender;
	private String name;
	private String relationDesc;
	private Date dateOfBirth;

}
