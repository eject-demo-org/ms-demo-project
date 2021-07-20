package com.demo.customer.data.entity;

import java.time.Instant;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "CU_FAMILY")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FamilyDeatils {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long familyId;
	private String baseId;
	private String spouseId;
	private String spouseName;
	private Instant marriageDate;
	private Integer childrenCount = 0;
	private Integer familyCount = 0;
	private Integer familyHeadId;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "DEMOGRAPHIC_ID")
	private DemographicDetails demographicDetails;
	
	@OneToMany(mappedBy = "familyDeatils", cascade = CascadeType.ALL)
	private List<FamilyRelationDetails> familyRelationDetails;
}
