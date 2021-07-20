package com.demo.customer.data.vo;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class DemographicSaveRequest {
	private String cuiid;
	private Long applicationNumber;
	private PersonInterfaceDataResponse personInterfaceDataResponse;
	private FamilyDataResponse familyDataResponse;

}
