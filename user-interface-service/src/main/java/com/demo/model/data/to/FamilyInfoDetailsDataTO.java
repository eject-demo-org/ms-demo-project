package com.demo.model.data.to;

import java.util.Date;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class FamilyInfoDetailsDataTO {
		
	private String relationId;
	private String id;
	private Integer relation;
	private Integer gender;
	private String name;
	private String relationDesc;
	private Date dateOfBirth;

}
