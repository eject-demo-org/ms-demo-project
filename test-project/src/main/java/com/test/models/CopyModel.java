package com.test.models;

import java.util.Set;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class CopyModel {
	
	@ApiModelProperty(notes = "Name of user", required = true)
	private String name;
	
	@ApiModelProperty(notes = "Ager of user", dataType = "Number")
	private Integer age;
	
	@ApiModelProperty(notes = "mail of user", dataType = "String")
	private String mail;
	
	@ApiModelProperty(notes = "Child Details")
	private CopyChildModel child;
	
	@ApiModelProperty(notes = "List of copy")
	private Set<CopySetModel> setC;

}
