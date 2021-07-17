package com.test.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import com.test.models.CopyModel;
import com.test.models.OriginalModel;

@Mapper(uses = SetModelMapper.class)
public interface ModelMapper {
	
	public ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);
	
	@Mappings({
		@Mapping(source = "email", target = "mail"),
		@Mapping(source = "childModel", target = "child"),
		@Mapping(source = "childModel.childBirthDate", target = "child.childDOB"),
		@Mapping(source = "setModelO", target = "setC")
		})
	public CopyModel mapOriginal2Copy(OriginalModel original);

}

