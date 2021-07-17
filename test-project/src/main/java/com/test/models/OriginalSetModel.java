package com.test.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class OriginalSetModel {
	
	private String nameSetO;
	private Integer numSetO;

}
