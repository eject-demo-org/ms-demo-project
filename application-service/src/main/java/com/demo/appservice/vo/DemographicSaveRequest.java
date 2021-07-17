package com.demo.appservice.vo;

import java.util.List;

import lombok.Data;

@Data
public class DemographicSaveRequest {
private String cuiid;
private Long applicationNumber;
private List<String> interfacesListToSaveDemographicData;

}
