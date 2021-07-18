package com.demo.appservice.vo;

import lombok.Data;

@Data
public class InterfaceResponseVO {
	private String status;
	private String message;
	private FamilyDataResponse familyData;
}
