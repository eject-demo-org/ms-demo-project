package com.demo.model.data.to;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class AddressDataTO {
	
	@JsonAlias("sNo")
	private Integer serialNumber;
	private String id;
	private Integer addressType;
	private String flatNumber;
	private String buildingName;
	private String street;
	private String area;
	private Integer city;
	private Integer state;
	private Long pinCode;

}
