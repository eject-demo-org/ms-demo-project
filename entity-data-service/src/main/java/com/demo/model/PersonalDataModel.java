package com.demo.model;

import java.util.Date;

public class PersonalDataModel {

	private final String uid;
	private String firstName;
	private String lastName;
	private Date dob;
	private Integer maritalCode;
	private Integer birthPlaceCode;
	private Integer genderCode;
	private Integer citizenshipCode;
			
	public PersonalDataModel(String uid) {
		this.uid = uid;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}	
	public Integer getMaritalCode() {
		return maritalCode;
	}
	public void setMaritalCode(Integer maritalCode) {
		this.maritalCode = maritalCode;
	}
	public Integer getBirthPlaceCode() {
		return birthPlaceCode;
	}
	public void setBirthPlaceCode(Integer birthPlaceCode) {
		this.birthPlaceCode = birthPlaceCode;
	}
	public Integer getGenderCode() {
		return genderCode;
	}
	public void setGenderCode(Integer genderCode) {
		this.genderCode = genderCode;
	}
	public Integer getCitizenshipCode() {
		return citizenshipCode;
	}
	public void setCitizenshipCode(Integer citizenshipCode) {
		this.citizenshipCode = citizenshipCode;
	}
	public String getUid() {
		return uid;
	}
	@Override
	public String toString() {
		return "PersonalDataModel [uid=" + uid + ", fName=" + firstName + ", lName=" + lastName + ", dob=" + dob
				+ ", maritalCode=" + maritalCode + ", birthPlaceCode=" + birthPlaceCode + ", genderCode=" + genderCode
				+ ", citizenshipCode=" + citizenshipCode + "]";
	}
	
	
}
