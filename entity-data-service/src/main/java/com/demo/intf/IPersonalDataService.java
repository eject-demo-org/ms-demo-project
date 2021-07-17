package com.demo.intf;

import java.util.Set;

import com.demo.model.AddressDataModel;
import com.demo.model.PersonalDataModel;

public interface IPersonalDataService {
		
	public PersonalDataModel getPersonalData(String uid);
	
	public Set<AddressDataModel> getAddressList(String uid);
	
}
