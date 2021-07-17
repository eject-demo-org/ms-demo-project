package com.demo.appservice.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.demo.appservice.vo.ApplicationCreationRequest;
import com.demo.appservice.vo.ApplicationCreationResponse;

@Component
@Qualifier("applicationService")
public interface ApplicationService {

	String verifyCustomerId(String cuiid , Locale locale,StringBuilder errorMessage);
	ApplicationCreationResponse createApplication(ApplicationCreationRequest application , Locale locale);
}
