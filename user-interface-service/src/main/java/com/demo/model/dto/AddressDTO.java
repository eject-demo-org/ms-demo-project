package com.demo.model.dto;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class AddressDTO {
	
	private Integer sNo;
	private Integer addressType;
	private String flatNo;
	private String buildingName;
	private String street;
	private String area;
	private Integer city;
	private Integer state;
	private Long pincode;
	
}
