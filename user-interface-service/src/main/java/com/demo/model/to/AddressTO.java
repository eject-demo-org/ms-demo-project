package com.demo.model.to;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class AddressTO {
	
	private Integer sNo;
	private Integer addressTypeCode;
	private String flatNo;
	private String buildingName;
	private String street;
	private String area;
	private Integer cityCode;
	private Integer stateCode;
	private Long pincode;

}
