package com.demo.response.model;

import org.springframework.http.HttpStatus;

import com.demo.model.FamilyDataModel;

public class FamilyDataReponse {
	
	HttpStatus responseCode;
	String responseDesc;
	FamilyDataModel familyData;
	
	public FamilyDataReponse(HttpStatus responseCode, String responseDesc, FamilyDataModel familyData) {
		super();
		this.responseCode = responseCode;
		this.responseDesc = responseDesc;
		this.familyData = familyData;
	}
	public HttpStatus getResponseCode() {
		return responseCode;
	}
	public void setResponseCode(HttpStatus responseCode) {
		this.responseCode = responseCode;
	}
	public String getResponseDesc() {
		return responseDesc;
	}
	public void setResponseDesc(String responseDesc) {
		this.responseDesc = responseDesc;
	}
	public FamilyDataModel getFamilyData() {
		return familyData;
	}
	public void setFamilyData(FamilyDataModel familyData) {
		this.familyData = familyData;
	}
}
