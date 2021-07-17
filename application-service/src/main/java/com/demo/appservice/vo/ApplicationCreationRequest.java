package com.demo.appservice.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApplicationCreationRequest {

	private Long applicationNumber;
	private String cuiid;
}
