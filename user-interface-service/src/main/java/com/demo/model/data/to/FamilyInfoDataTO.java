package com.demo.model.data.to;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class FamilyInfoDataTO {
	
	@JsonAlias("baseId")
	private String id;
	private String spouseId;
	private String spouseName;
	private Date marriageDate;
	private Integer childrenCount;
	private Integer familyCount;
	private String familyHeadId;
	
	@JsonAlias("details")
	private List<FamilyInfoDetailsDataTO> familyDetails;

}
