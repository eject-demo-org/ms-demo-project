package com.demo.model.data.base.to;

import org.springframework.http.HttpStatus;

import com.demo.model.data.to.FamilyInfoDataTO;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class FamilyInfoDataBaseTO {

	private HttpStatus responseCode;
	private String responseDesc;
	
	private FamilyInfoDataTO familyData;
}
