package com.demo.customer.data.vo;

import lombok.Data;

@Data
public class FamilyRelationDetailsVO{
		private Long familyRelationId;
		private Integer relation;
		private Integer gender;
		private	String name;
		private String relationDesc;

	}