package com.test.models;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class CopyChildModel {
	
	@ApiModelProperty(notes = "child name")
	private String childName;
	
	@ApiModelProperty(notes = "child date of birth", dataType = "Date")
	private Date childDOB;

}
