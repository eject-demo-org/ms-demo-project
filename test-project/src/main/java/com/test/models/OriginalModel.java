package com.test.models;

import java.math.BigDecimal;
import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(includeFieldNames = true)
public class OriginalModel {
	
	private String name;
	private Integer age;
	private String email;
	private BigDecimal salary;
	
	private OriginalChildModel childModel;
	
	private Set<OriginalSetModel> setModelO;

}
