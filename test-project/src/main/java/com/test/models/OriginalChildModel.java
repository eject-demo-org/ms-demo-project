package com.test.models;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class OriginalChildModel {

	private String childName;
	private Date childBirthDate;
	
}
