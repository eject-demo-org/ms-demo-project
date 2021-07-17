package com.demo.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.demo.model.data.to.FamilyInfoDataTO;
import com.demo.model.dto.FamilyInfoDTO;
import com.demo.model.to.FamilyInfoTO;

@Mapper(uses = FamilyInfoDetailsMapper.class)
public interface FamilyInfoMapper {

	public FamilyInfoMapper INSTANCE = Mappers.getMapper(FamilyInfoMapper.class);

	@Mappings({
		@Mapping(target = "uuid", ignore = true),
		@Mapping(source = "familyDetails", target = "details")
	})
	public FamilyInfoDTO mapFamilyInfoDataTO2DTO(FamilyInfoDataTO dataTO);

	@Mapping(source = "details", target = "familyDetails")
	public FamilyInfoTO mapFamilyInfoDTO2TO(FamilyInfoDTO dto);

}
