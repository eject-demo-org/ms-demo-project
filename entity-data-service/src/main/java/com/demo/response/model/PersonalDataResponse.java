package com.demo.response.model;

import java.util.Set;

import org.springframework.http.HttpStatus;

import com.demo.model.AddressDataModel;
import com.demo.model.PersonalDataModel;


public class PersonalDataResponse {
	
	HttpStatus responseCode;
	String responseDesc;
	PersonalDataModel personal;
	Set<AddressDataModel> address;

	public PersonalDataResponse(HttpStatus responseCode, String responseDesc, PersonalDataModel personal) {		
		this.responseCode = responseCode;
		this.personal = personal;
		this.responseDesc = responseDesc;
	}

	public HttpStatus getResponseCode() {
		return responseCode;
	}

	public String getResponseDesc() {
		return responseDesc;
	}
	
	public PersonalDataModel getPersonal() {
		return personal;
	}

	public Set<AddressDataModel> getAddress() {
		return address;
	}

	public void setAddress(Set<AddressDataModel> address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "PersonalDataResponse [responseCode=" + responseCode + ", responseDesc=" + responseDesc + ", personal="
				+ personal + ", address=" + address + "]";
	}

	
	
}
