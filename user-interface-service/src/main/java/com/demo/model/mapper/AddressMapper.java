package com.demo.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.demo.model.data.to.AddressDataTO;
import com.demo.model.dto.AddressDTO;
import com.demo.model.to.AddressTO;

@Mapper
public interface AddressMapper {

	AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

	@Mappings({
		@Mapping(source = "serialNumber", target = "SNo"),
		@Mapping(source = "flatNumber", target = "flatNo"),
		@Mapping(source = "pinCode", target = "pincode")
	})
	AddressDTO mapAddressDataTO2DTO(AddressDataTO data);

	@Mappings({
		@Mapping(source = "addressType", target = "addressTypeCode"),
		@Mapping(source = "city", target = "cityCode"),
		@Mapping(source = "state", target = "stateCode")
	})
	AddressTO mapAddressDTO2TO(AddressDTO dto); 
}
