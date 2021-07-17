package com.demo.appservice.vo;

import lombok.Data;

@Data
public class DemographicSaveRequest {
private String cuiid;
private Long applicationNumber;
private PersonInterfaceDataResponse personInterfaceDataResponse;


}
