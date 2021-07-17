package com.demo.model.to;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class FamilyInfoDetailsTO {
	
	private String relationId;
	private String id;
	private Integer relationCode;
	private Integer genderCode;
	private String name;
	private String relationDesc;
	private Date birthDate;

}
