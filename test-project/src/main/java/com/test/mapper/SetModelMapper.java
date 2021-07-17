package com.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.test.models.CopySetModel;
import com.test.models.OriginalSetModel;

@Mapper
public interface SetModelMapper {
	
	public SetModelMapper INSTANCE = Mappers.getMapper(SetModelMapper.class);
	
	@Mappings({
		@Mapping(source = "nameSetO", target = "nameSetC"),
		@Mapping(source = "numSetO", target = "numSetC")
	})
	public CopySetModel mapOriginalSet2CopySet(OriginalSetModel originalSet);

}
