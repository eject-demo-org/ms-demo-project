package com.demo.model;

public class AddressDataModel {
	
	private Integer sNo;
	private String id;
	private Integer addressType;
	private String flatNumber;
	private String buildingName;
	private String street;
	private String area;
	private Integer city;
	private Integer state;
	private Long pinCode;
	
	
	public AddressDataModel(String id) {
		this.id = id;
	}
	
	public Integer getsNo() {
		return sNo;
	}
	public void setsNo(Integer sNo) {
		this.sNo = sNo;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public Integer getAddressType() {
		return addressType;
	}

	public void setAddressType(Integer addressType) {
		this.addressType = addressType;
	}

	public String getFlatNumber() {
		return flatNumber;
	}
	public void setFlatNumber(String flatNumber) {
		this.flatNumber = flatNumber;
	}
	public String getBuildingName() {
		return buildingName;
	}
	public void setBuildingName(String buildingName) {
		this.buildingName = buildingName;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public Integer getCity() {
		return city;
	}
	public void setCity(Integer city) {
		this.city = city;
	}
	public Integer getState() {
		return state;
	}
	public void setState(Integer state) {
		this.state = state;
	}
	public Long getPinCode() {
		return pinCode;
	}
	public void setPinCode(Long pinCode) {
		this.pinCode = pinCode;
	}

	@Override
	public String toString() {
		return "AddressDataModel [sNo=" + sNo + ", id=" + id + ", addressType=" + addressType + ", flatNumber="
				+ flatNumber + ", buildingName=" + buildingName + ", street=" + street + ", area=" + area + ", city="
				+ city + ", state=" + state + ", pinCode=" + pinCode + "]";
	}
	
	
}
