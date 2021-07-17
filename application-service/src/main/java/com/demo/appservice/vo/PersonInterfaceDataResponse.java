package com.demo.appservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonInterfaceDataResponse {

	private String status;

	private String message;

	private ResponseDTO responseTO;

}
