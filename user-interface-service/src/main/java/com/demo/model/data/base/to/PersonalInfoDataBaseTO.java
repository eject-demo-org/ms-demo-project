package com.demo.model.data.base.to;

import java.util.Set;

import org.springframework.http.HttpStatus;

import com.demo.model.data.to.AddressDataTO;
import com.demo.model.data.to.PersonalInfoDataTO;
import com.fasterxml.jackson.annotation.JsonAlias;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
public class PersonalInfoDataBaseTO {
	
	HttpStatus responseCode;
	String responseDesc;
	
	@JsonAlias("personal")
	PersonalInfoDataTO personalDataTO;
	
	@JsonAlias("address")
	Set<AddressDataTO> addressDataTO;
	
}
