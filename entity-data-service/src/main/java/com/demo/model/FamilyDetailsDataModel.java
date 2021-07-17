package com.demo.model;

import java.util.Date;

public class FamilyDetailsDataModel {
	
	private String relationId;
	private String id;
	private Integer relation;
	private Integer gender;
	private String name;
	private String relationDesc;
	private Date dateOfBirth;
			
	public FamilyDetailsDataModel(String relationId) {
		super();
		this.relationId = relationId;
	}
	public String getRelationId() {
		return relationId;
	}	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Integer getRelation() {
		return relation;
	}
	public void setRelation(Integer relation) {
		this.relation = relation;
	}
	public Integer getGender() {
		return gender;
	}
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRelationDesc() {
		return relationDesc;
	}
	public void setRelationDesc(String relationDesc) {
		this.relationDesc = relationDesc;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	

}
