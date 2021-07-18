package com.demo.appservice.serviceimpl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.demo.appservice.application.constants.ApplicationConstants;
import com.demo.appservice.service.ApplicationService;
import com.demo.appservice.vo.ApplicationCreationRequest;
import com.demo.appservice.vo.ApplicationCreationResponse;
import com.demo.appservice.vo.DemographicSaveRequest;
import com.demo.appservice.vo.FamilyDataResponse;
import com.demo.appservice.vo.HttpReponseStatus;
import com.demo.appservice.vo.InterfaceResponseVO;
import com.demo.appservice.vo.PersonInterfaceDataResponse;

import lombok.extern.slf4j.Slf4j;
@Service
@Transactional
@Slf4j
public class ApplicationServiceImpl implements ApplicationService{

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private MessageSource messageSource;

	@Override
	public String verifyCustomerId(String cuiid , Locale locale,StringBuilder errorMessage) {
		return isCustomerIdValid(cuiid,errorMessage) ?  ApplicationConstants.SUCCESS : ApplicationConstants.FAILED;
	}

	private boolean isCustomerIdValid(String cuiid,StringBuilder errorMessage) {
		if(cuiid.length() < 15 ) {
			errorMessage.append("application.creation.error.msg_2");
			return false;
		}else if(cuiid.length() > 15) {
			errorMessage.append("application.creation.error.msg_3");
			return false;
		}else if(!cuiid.matches("[0-9]+")) {//only number allowed as CUIID
			errorMessage.append("application.creation.error.msg_4");
			return false;
		}
		return true;
	}

	@Override
	public ApplicationCreationResponse createApplication(ApplicationCreationRequest application , Locale locale) {
		ApplicationCreationResponse applicationCreationResponse = null;
		//Step 1 : verify Customer Id
		StringBuilder errorMessage = new StringBuilder(ApplicationConstants.BLANK_STRING);
		if(validateApplicationCreationRequest(application , errorMessage ) && ApplicationConstants.SUCCESS.equals(verifyCustomerId(application.getCuiid(), locale ,errorMessage))) {
			applicationCreationResponse = applicationCreationWorkFlow(application,locale);
		}else {
			applicationCreationResponse = new ApplicationCreationResponse();
			applicationCreationResponse.setResponseStatus
			(setHttpResponseStatus(ApplicationConstants.FAILED, errorMessage.toString(),locale,true));
		}
		return applicationCreationResponse;
	}

	private ApplicationCreationResponse applicationCreationWorkFlow(ApplicationCreationRequest application , Locale locale) {
		ApplicationCreationResponse applicationCreationResponse = new ApplicationCreationResponse();
		try {
			//calltheInterfaces for refreshing the data
			Object object = callInterfacesRefresh(application.getCuiid() , ApplicationConstants.APPLICATION_CREATION_FLOW,locale);
			if(object != null && object instanceof PersonInterfaceDataResponse) {//means we got the response as needed for application creation flow
				Map<Boolean , String > saveDemographicResultMap = saveDemographicData(application.getCuiid(),application.getApplicationNumber(),
						(PersonInterfaceDataResponse)object ,locale);
				if(saveDemographicResultMap.containsKey(true)) {
					applicationCreationResponse.setApplicationNumber(new Random().nextLong());
					applicationCreationResponse.setCuiid(application.getCuiid());
					applicationCreationResponse.setResponseStatus
					(setHttpResponseStatus(ApplicationConstants.SUCCESS, saveDemographicResultMap.get(true) , locale , true));
				}else {
					String errorKey = saveDemographicResultMap.get(false);
					applicationCreationResponse.setResponseStatus
					(setHttpResponseStatus(ApplicationConstants.FAILED,errorKey ,locale,false));
				}
			}else {
				//error response need to send to application
				String errorKey = "application.creation.personal.interface.error";
				applicationCreationResponse.setResponseStatus
				(setHttpResponseStatus(ApplicationConstants.FAILED,errorKey ,locale,true));
			}
		} catch (Exception exception) {
			String errorKey = "application.creation.personal.interface.error";
			applicationCreationResponse.setResponseStatus
			(setHttpResponseStatus(ApplicationConstants.FAILED,errorKey ,locale,true));
		}
		return applicationCreationResponse;
	}

	private HttpReponseStatus setHttpResponseStatus(String status , String statusDesc , Locale locale , boolean isMessageSourceRequest) {
		HttpReponseStatus httpReponseStatus = new HttpReponseStatus();
		httpReponseStatus.setStatus(status);
		if(isMessageSourceRequest)
			httpReponseStatus.setStatusDesc(messageSource.getMessage(statusDesc, null, locale));
		else
			httpReponseStatus.setStatusDesc(statusDesc);
		return httpReponseStatus;
	}

	private boolean validateApplicationCreationRequest(ApplicationCreationRequest application , StringBuilder errorMessage) {
		if(application.getCuiid() == null || application.getCuiid().isEmpty()) {
			errorMessage.append("application.creation.error.msg_1");
			return false;
		}
		return true;
	}

	private Object callInterfacesRefresh(String cuiid,String applicationEvent ,Locale locale) throws RestClientException{
		Object object = null;
		//rest template will call to another MicroServices for refreshing the interface data
		List<String> interfacesList = ApplicationConstants.getInterfaceListByApplicationEvent(applicationEvent);
		log.info("**************callInterfaces***************************"+interfacesList);
		if(interfacesList.contains(ApplicationConstants.INTERFACE_NAME_PERSONAL_INFO)){
			object  = getPersonInterfaceData( cuiid, locale);
		}else if(interfacesList.contains(ApplicationConstants.INTERFACE_NAME_FAMILY_INFO)){
			object = getFamilyInterfaceData(cuiid, locale);
		}
		return object;
	}
	private PersonInterfaceDataResponse getPersonInterfaceData( String cuiid, Locale locale)throws RestClientException {
		PersonInterfaceDataResponse personInterfaceDataResponse = null;
		try {
			ResponseEntity<PersonInterfaceDataResponse> responseEntity = restTemplate.getForEntity(
					ApplicationConstants.URI_FOR_PERSONAL_INFO+"interfaceName="+ApplicationConstants.INTERFACE_NAME_PERSONAL_INFO+"&uid="+cuiid,
					PersonInterfaceDataResponse.class);
			if (responseEntity != null &&responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
				personInterfaceDataResponse =  responseEntity.getBody();
			}
		} catch (RestClientException exception) {
			log.error("******************Exception caught in calling of getPersonInterfaceData*****************"
					+ exception + "***" + exception.getMessage());
			throw exception;

		}
		return personInterfaceDataResponse ;
	}


	private Map<Boolean, String > saveDemographicData(String uuid , Long applicationNumber , PersonInterfaceDataResponse personInterfaceDataResponse , Locale locale) {
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		List<Locale> localeList = new ArrayList<>();
		localeList.add(locale);
		headers.setAcceptLanguageAsLocales(localeList);
		//prepare request for demographic save
		DemographicSaveRequest demographicSaveRequest = new DemographicSaveRequest();
		demographicSaveRequest.setApplicationNumber(applicationNumber);
		demographicSaveRequest.setCuiid(uuid);
		demographicSaveRequest.setPersonInterfaceDataResponse(personInterfaceDataResponse);
		HttpEntity<?> entity = new HttpEntity<DemographicSaveRequest>(demographicSaveRequest, headers);

		ResponseEntity<HttpReponseStatus> responseEntity = restTemplate.postForEntity("http://CUSTOMER-DATA-SERVICE/customerData/saveDemographicData", entity, HttpReponseStatus.class);
		log.info("demographic service called"+responseEntity.getBody());
		//validate demographic save event for application creation event
		return validateDemographicServiceResponse(responseEntity);
	}
	private Map<Boolean , String > validateDemographicServiceResponse(ResponseEntity<HttpReponseStatus> entity) {
		Map<Boolean , String > resultMap = new LinkedHashMap<>();
		HttpReponseStatus responseStatus = entity.getBody();
		if(entity.getStatusCode() == HttpStatus.OK && ApplicationConstants.SUCCESS.equalsIgnoreCase(responseStatus.getStatus())) {
			resultMap.put(true, "application.creation.success.msg");
		}else {
			resultMap.put(false, responseStatus.getStatusDesc());
		}
		return resultMap;
	}

	private InterfaceResponseVO getFamilyInterfaceData( String cuiid, Locale locale)throws RestClientException {
		InterfaceResponseVO familyDataResponse = null;
		try {
			ResponseEntity<InterfaceResponseVO> responseEntity = restTemplate.getForEntity(
					ApplicationConstants.URI_FOR_PERSONAL_INFO+"interfaceName="+ApplicationConstants.INTERFACE_NAME_FAMILY_INFO+"&uid="+cuiid,
					InterfaceResponseVO.class);
			if (responseEntity != null &&responseEntity.getStatusCode() == HttpStatus.OK && responseEntity.getBody() != null) {
				familyDataResponse =  responseEntity.getBody();
			}
		} catch (RestClientException exception) {
			log.error("******************Exception caught in calling of getPersonInterfaceData*****************"
					+ exception + "***" + exception.getMessage());
			throw exception;

		}
		return familyDataResponse ;
	}

	private Map<Boolean, String > saveFamilyData(String uuid , Long applicationNumber , FamilyDataResponse familyDataResponse , Locale locale) {
		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		List<Locale> localeList = new ArrayList<>();
		localeList.add(locale);
		headers.setAcceptLanguageAsLocales(localeList);
		//prepare request for demographic save
		DemographicSaveRequest demographicSaveRequest = new DemographicSaveRequest();
		demographicSaveRequest.setApplicationNumber(applicationNumber);
		demographicSaveRequest.setCuiid(uuid);
		demographicSaveRequest.setFamilyDataResponse(familyDataResponse);
		HttpEntity<?> entity = new HttpEntity<DemographicSaveRequest>(demographicSaveRequest, headers);

		ResponseEntity<HttpReponseStatus> responseEntity = restTemplate.postForEntity("http://CUSTOMER-DATA-SERVICE/customerData/saveFamilyData", entity, HttpReponseStatus.class);
		log.info("demographic service called"+responseEntity.getBody());
		//validate demographic save event for application creation event
		return validateFamilyServiceResponse(responseEntity);
	}
	private Map<Boolean , String > validateFamilyServiceResponse(ResponseEntity<HttpReponseStatus> entity) {
		Map<Boolean , String > resultMap = new LinkedHashMap<>();
		HttpReponseStatus responseStatus = entity.getBody();
		if(entity.getStatusCode() == HttpStatus.OK && ApplicationConstants.SUCCESS.equalsIgnoreCase(responseStatus.getStatus())) {
			resultMap.put(true, "application.family.data.saved");
		}else {
			resultMap.put(false, responseStatus.getStatusDesc());
		}
		return resultMap;
	}

	@Override
	public HttpReponseStatus callAndSaveByInterface(ApplicationCreationRequest applicationCreationRequest,
			String interfaceName, Locale locale) {
		HttpReponseStatus httpReponseStatus = new HttpReponseStatus();
		try {
			if(ApplicationConstants.INTERFACE_NAME_FAMILY_INFO.equalsIgnoreCase(interfaceName)) {
				InterfaceResponseVO familyDataResponse = 	getFamilyInterfaceData(applicationCreationRequest.getCuiid(), locale);
				if(familyDataResponse != null && familyDataResponse.getStatus().equalsIgnoreCase(ApplicationConstants.STATUS_FOUND)) {
					Map<Boolean , String > saveDemographicResultMap = saveFamilyData(applicationCreationRequest.getCuiid(),applicationCreationRequest.getApplicationNumber(),
							familyDataResponse.getFamilyDataResponse() ,locale);
					if(saveDemographicResultMap.containsKey(true)) {
						httpReponseStatus = setHttpResponseStatus(ApplicationConstants.SUCCESS, saveDemographicResultMap.get(true) , locale , true);
					}else {
						String errorKey = saveDemographicResultMap.get(false);
						httpReponseStatus = setHttpResponseStatus(ApplicationConstants.FAILED,errorKey ,locale,false);
					}
				}else {
					//error response need to send to application
					String errorKey = "application.creation.family.interface.error";
					httpReponseStatus = setHttpResponseStatus(ApplicationConstants.FAILED,errorKey ,locale,true);
				}
			}
		} catch (Exception exception) {
			httpReponseStatus = setHttpResponseStatus(ApplicationConstants.FAILED,"application.creation.family.interface.error" ,locale,true);
		}
		return httpReponseStatus;
	}

}
