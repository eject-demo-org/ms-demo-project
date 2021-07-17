package com.demo.model;


import org.springframework.http.HttpStatus;

import com.demo.model.to.InterfaceResponseTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(includeFieldNames = true)
@AllArgsConstructor
public class InterfaceResponseModel {
	
	private HttpStatus status;
	private String message;
	
	private InterfaceResponseTO responseTO;

}
