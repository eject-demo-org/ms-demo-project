package com.demo.customer.data.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "CU_FAMILY_RELATION")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyRelationDetails {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long familyRelationId;
	private Integer relation;
	private Integer gender;
	private	String name;
	private String relationDesc;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "FAMILY_ID")
	private FamilyDeatils familyDeatils;
}
