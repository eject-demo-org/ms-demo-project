package com.test.models;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(includeFieldNames = true)
public class CopySetModel {

	@ApiModelProperty(notes = "Name of set copy")
	private String nameSetC;
	private Long numSetC;
}
