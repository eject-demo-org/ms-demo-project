package com.demo.model.to;

import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class FamilyInfoTO implements InterfaceResponseTO {
	
	private String id;
	private String spouseId;
	private String spouseName;
	private Date marriageDate;
	private Integer childrenCount;
	private Integer familyCount;
	private String familyHeadId;
	
	List<FamilyInfoDetailsTO> familyDetails;
	
}
