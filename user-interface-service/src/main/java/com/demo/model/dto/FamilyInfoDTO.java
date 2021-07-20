package com.demo.model.dto;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Document(collection = "familyInfo")
public class FamilyInfoDTO {
	
	private UUID uuid;
	private String id;
	private String spouseId;
	private String spouseName;
	private Date marriageDate;
	private Integer childrenCount;
	private Integer familyCount;
	private String familyHeadId;
	
	private List<FamilyInfoDetailsDTO> details;

}
