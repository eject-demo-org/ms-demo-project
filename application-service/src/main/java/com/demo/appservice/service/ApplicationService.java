package com.demo.appservice.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.demo.appservice.vo.ApplicationCreationRequest;
import com.demo.appservice.vo.ApplicationCreationResponse;
import com.demo.appservice.vo.HttpReponseStatus;

@Component
@Qualifier("applicationService")
public interface ApplicationService {

	String verifyCustomerId(String cuiid , Locale locale,StringBuilder errorMessage);
	ApplicationCreationResponse createApplication(ApplicationCreationRequest application , Locale locale);
	
	HttpReponseStatus callAndSaveByInterface(ApplicationCreationRequest applicationCreationRequest , String interfaceName , Locale locale);
	
}
