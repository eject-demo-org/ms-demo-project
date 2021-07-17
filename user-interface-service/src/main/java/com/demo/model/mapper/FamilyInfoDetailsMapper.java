package com.demo.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.demo.model.dto.FamilyInfoDetailsDTO;
import com.demo.model.to.FamilyInfoDetailsTO;

@Mapper
public interface FamilyInfoDetailsMapper {
	
	FamilyInfoDetailsMapper INSTANCE = Mappers.getMapper(FamilyInfoDetailsMapper.class);
	
	@Mappings({
		@Mapping(source = "relation", target = "relationCode"),
		@Mapping(source = "gender", target = "genderCode"),
		@Mapping(source = "dateOfBirth", target = "birthDate")
	})
	public FamilyInfoDetailsTO mapFamilyInfoDetailsDTO2TO(FamilyInfoDetailsDTO dto);
}
