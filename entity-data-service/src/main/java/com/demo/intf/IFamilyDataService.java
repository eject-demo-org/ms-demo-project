package com.demo.intf;

import java.util.List;

import com.demo.model.FamilyDataModel;
import com.demo.model.FamilyDetailsDataModel;

public interface IFamilyDataService {
	
	public FamilyDataModel getFamilyData(String id);
	
	public List<FamilyDetailsDataModel> getFamilyDetails(String id);

}
