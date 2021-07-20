package com.demo.appservice.vo;

import java.time.Instant;
import java.util.List;

import lombok.Data;

@Data
public class FamilyDataResponse {
	private String baseId;
	private String spouseId;
	private String spouseName;
	private Instant marriageDate;
	private Integer childrenCount = 0;
	private Integer familyCount = 0;
	private Integer familyHeadId;
	private List<FamilyRelationDetailsVO> details;
	
}
