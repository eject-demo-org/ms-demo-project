package com.demo.model;

import java.util.Date;
import java.util.List;

public class FamilyDataModel {
	
	private String baseId;
	private String spouseId;
	private String spouseName;
	private Date marriageDate;
	private Integer childrenCount;
	private Integer familyCount;
	private String familyHeadId;
	
	private List<FamilyDetailsDataModel> details;
	
	public String getBaseId() {
		return baseId;
	}
	public void setBaseId(String baseId) {
		this.baseId = baseId;
	}
	public String getSpouseId() {
		return spouseId;
	}
	public void setSpouseId(String spouseId) {
		this.spouseId = spouseId;
	}
	public String getSpouseName() {
		return spouseName;
	}
	public void setSpouseName(String spouseName) {
		this.spouseName = spouseName;
	}
	public Date getMarriageDate() {
		return marriageDate;
	}
	public void setMarriageDate(Date marriageDate) {
		this.marriageDate = marriageDate;
	}
	public Integer getChildrenCount() {
		return childrenCount;
	}
	public void setChildrenCount(Integer childrenCount) {
		this.childrenCount = childrenCount;
	}
	public Integer getFamilyCount() {
		return familyCount;
	}
	public void setFamilyCount(Integer familyCount) {
		this.familyCount = familyCount;
	}
	public String getFamilyHeadId() {
		return familyHeadId;
	}
	public void setFamilyHeadId(String familyHeadId) {
		this.familyHeadId = familyHeadId;
	}
	public List<FamilyDetailsDataModel> getDetails() {
		return details;
	}
	public void setDetails(List<FamilyDetailsDataModel> details) {
		this.details = details;
	}
	
}
