package com.demo.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.demo.model.data.to.PersonalInfoDataTO;
import com.demo.model.dto.PersonalInfoDTO;
import com.demo.model.to.PersonalInfoTO;

@Mapper(componentModel = "spring")
public interface PersonInfoMapper {
	
	public PersonInfoMapper INSTANCE = Mappers.getMapper(PersonInfoMapper.class);
	
	@Mappings({
		@Mapping(ignore = true, target = "addresses"),
		@Mapping(source = "birthPlace", target = "birthPlaceCode"),
		@Mapping(source = "citizenship", target = "citizenshipCode"),
		@Mapping(source = "gender", target = "genderCode"),
		@Mapping(source = "maritalStatus", target = "maritalStatusCode")
	})
	public PersonalInfoTO mapPersonalInfoDTO2TO(PersonalInfoDTO dto);
	
	@Mappings({
		@Mapping (target = "addresses", ignore = true),
		@Mapping (target = "id", ignore = true),
		@Mapping (target = "uniqueId", ignore = true),
		@Mapping (source = "dob", target = "birthDate"),
		@Mapping (source = "citizenshipCode", target = "citizenship"),
		@Mapping (source = "birthPlaceCode", target = "birthPlace"),
		@Mapping (source = "FName", target = "firstName"),
		@Mapping (source = "LName", target = "lastName"),
		@Mapping (source = "genderCode", target = "gender"),
		@Mapping (source = "maritalCode", target = "maritalStatus")			
	})
	public PersonalInfoDTO mapPersonalInfoDataTO2DTO(PersonalInfoDataTO dataTO);

}
