package com.demo.factory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.demo.service.InterfaceCallService;
import com.demo.util.ApplicationConstant;

@Component("interfaceCallFactory")
public class InterfaceCallFactory {
	
	
	@Autowired
	@Qualifier("familyInfoService")
	private InterfaceCallService familyInfoService;
	
	@Autowired
	@Qualifier("personalInfoService")
	private InterfaceCallService personalInfoService;
	
	
	public InterfaceCallService getInterfaceService(String interfaceName) {
		System.out.println("interfaceName:"+interfaceName);
		if(ApplicationConstant.PERSONAL_INTERFACE_CODE.equalsIgnoreCase(interfaceName)) {
			return personalInfoService;
		} else if(ApplicationConstant.FAMILY_INTERFACE_CODE.equalsIgnoreCase(interfaceName)) {
			return familyInfoService;
		}
		
		return null;
	}

	
}
