package com.demo.customer.data.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.demo.customer.data.constants.CustomerDataDetailsConstants;
import com.demo.customer.data.entity.Addresses;
import com.demo.customer.data.entity.DemographicDetails;
import com.demo.customer.data.entity.FamilyDeatils;
import com.demo.customer.data.entity.FamilyRelationDetails;
import com.demo.customer.data.repository.AddressesRepository;
import com.demo.customer.data.repository.DemographicReposotory;
import com.demo.customer.data.repository.FamilyDetailsRepository;
import com.demo.customer.data.repository.FamilyRelationDetailsRepository;
import com.demo.customer.data.service.CustomerDataDetailsService;
import com.demo.customer.data.vo.AddressVO;
import com.demo.customer.data.vo.DemographicSaveRequest;
import com.demo.customer.data.vo.FamilyDataResponse;
import com.demo.customer.data.vo.HttpReponseStatus;
import com.demo.customer.data.vo.PersonInterfaceDataResponse;
import com.demo.customer.data.vo.ResponseDTO;

import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class CustomerDataDetailsServiceImpl implements CustomerDataDetailsService {

	@Autowired
	private DemographicReposotory demographicReposotory;

	@Autowired
	private AddressesRepository addressesRepository;
	
	@Autowired
	private FamilyRelationDetailsRepository familyRelationDetailsRepository;
	
	@Autowired
	private FamilyDetailsRepository familyDetailsRepository;;


	@Autowired
	private MessageSource messageSource;

	@Override
	public DemographicDetails getDemographicDetailsByCuiidAndApplicationNumber(DemographicDetails demographicDetails,
			Locale locale) {
		return demographicReposotory.findByApplicationNumberAndCuiid(demographicDetails.getApplicationNumber(),
				demographicDetails.getCuiid());
	}

	@Override
	public HttpReponseStatus saveDemographicDetails(DemographicSaveRequest demographicSaveRequest, Locale locale) {
		HttpReponseStatus httpReponseStatus = new HttpReponseStatus();
		try {

			if (validateSaveDemographicRequest(demographicSaveRequest, httpReponseStatus,locale)) {
				savePersonalInterfaceData(demographicSaveRequest, demographicSaveRequest.getPersonInterfaceDataResponse());
				httpReponseStatus.setStatus(CustomerDataDetailsConstants.SUCCESS);
				httpReponseStatus
				.setStatusDesc(messageSource.getMessage("application.demographic.data.saved", null, locale));
			}else if(!CustomerDataDetailsConstants.FAILED.equalsIgnoreCase(httpReponseStatus.getStatus())) {
				httpReponseStatus.setStatus(CustomerDataDetailsConstants.FAILED);
				httpReponseStatus
				.setStatusDesc(messageSource.getMessage("application.demographic.interface.data.error_4", new Object[] {CustomerDataDetailsConstants.INTERFACE_NAME_PERSONAL_INFO}, locale));

			}
		}catch(Exception exception) {
			httpReponseStatus.setStatus(CustomerDataDetailsConstants.FAILED);
			httpReponseStatus
			.setStatusDesc(messageSource.getMessage("application.demographic.interface.data.error_3", new Object[] {CustomerDataDetailsConstants.INTERFACE_NAME_PERSONAL_INFO}, locale));
		}
		return httpReponseStatus;
	}
	private boolean validateSaveDemographicRequest(DemographicSaveRequest demographicSaveRequest , HttpReponseStatus httpReponseStatus,Locale locale) {
		boolean isVlidateRequest = true;
		if(demographicSaveRequest.getCuiid() == null) {
			isVlidateRequest = false;
		}else if(demographicSaveRequest.getPersonInterfaceDataResponse() == null) {
			isVlidateRequest = false;
		}else if(demographicSaveRequest.getPersonInterfaceDataResponse().getResponseTO() == null) {
			isVlidateRequest = false;
		}
		if(!isVlidateRequest) {
			httpReponseStatus.setStatus(CustomerDataDetailsConstants.FAILED);
			httpReponseStatus
			.setStatusDesc(messageSource.getMessage("application.demographic.interface.data.error_4", new Object[] {CustomerDataDetailsConstants.INTERFACE_NAME_PERSONAL_INFO}, locale));
		}
		return isVlidateRequest;
	}

	public void savePersonalInterfaceData(DemographicSaveRequest demographicSaveRequest,
			PersonInterfaceDataResponse personInterfaceDataResponse)throws Exception {

		try {
			DemographicDetails demographicDetails = demographicReposotory.findByApplicationNumberAndCuiid(
					demographicSaveRequest.getApplicationNumber(), demographicSaveRequest.getCuiid());
			if (demographicDetails == null)
				demographicDetails = new DemographicDetails();
			ResponseDTO responseDTO = personInterfaceDataResponse.getResponseTO();

			demographicDetails.setBirthDate(responseDTO.getBirthDate());
			demographicDetails.setBirthPlaceCode(responseDTO.getBirthPlaceCode());
			demographicDetails.setCitizenshipCode(responseDTO.getCitizenshipCode());
			demographicDetails.setFName(responseDTO.getFirstName());
			demographicDetails.setLName(responseDTO.getLastName());
			demographicDetails.setGenderCode(responseDTO.getGenderCode());
			demographicDetails.setMaritalStatusCode(responseDTO.getMaritalStatusCode());
			if(responseDTO.getAddresses() != null && !responseDTO.getAddresses().isEmpty() ) {
				List<AddressVO> addressVOs = responseDTO.getAddresses() ;
				log.info("address*****************" + addressVOs);
				populateAddress(addressVOs, demographicDetails);
			}
			// saving the data in Parent table demographic
			demographicReposotory.save(demographicDetails);
		} catch (Exception e) {
			throw e;
		}

	}

	private void populateAddress(List<AddressVO> addressVO, DemographicDetails demographicDetails)throws Exception {
		if (addressVO != null) {
			List<Addresses> addressesList = new ArrayList<>();
			for (AddressVO vo : addressVO) {
				Addresses addresses = addressesRepository.findByFlatNoAndBuildingNameAndPincodeAndAddressTypeCode(
						vo.getFlatNo(), vo.getBuildingName(), vo.getPincode(), vo.getAddressTypeCode());
				if (null == addresses)
					addresses = new Addresses();
				addresses.setAddressTypeCode(vo.getAddressTypeCode());
				addresses.setArea(vo.getArea());
				addresses.setBuildingName(vo.getBuildingName());
				addresses.setCityCode(vo.getCityCode());
				addresses.setDemographicDetails(demographicDetails);
				addresses.setFlatNo(vo.getFlatNo());
				addresses.setPincode(vo.getPincode());
				addresses.setStateCode(vo.getStateCode());
				addresses.setStreet(vo.getStreet());
				addressesList.add(addresses);
				demographicDetails.setAddresses(addressesList);
			}

		}
	}

	@Override
	public HttpReponseStatus saveFamilyData(DemographicSaveRequest demographicSaveRequest, Locale locale) {
		HttpReponseStatus httpReponseStatus = new HttpReponseStatus();
		try {
			if(validateSaveFamilyData(demographicSaveRequest , httpReponseStatus,locale)) {
				saveFamilyData(demographicSaveRequest);
				httpReponseStatus.setStatus(CustomerDataDetailsConstants.SUCCESS);
				httpReponseStatus
				.setStatusDesc(messageSource.getMessage("application.family.data.saved", null, locale));
			}else if(!CustomerDataDetailsConstants.FAILED.equalsIgnoreCase(httpReponseStatus.getStatus())) {
				httpReponseStatus.setStatus(CustomerDataDetailsConstants.FAILED);
				httpReponseStatus
				.setStatusDesc(messageSource.getMessage("application.demographic.interface.data.error_4", new Object[] {CustomerDataDetailsConstants.INTERFACE_NAME_FAMILY_INFO}, locale));

			}
		} catch (Exception exception) {
			httpReponseStatus.setStatus(CustomerDataDetailsConstants.FAILED);
			httpReponseStatus
			.setStatusDesc(messageSource.getMessage("application.demographic.interface.data.error_3", new Object[] {CustomerDataDetailsConstants.INTERFACE_NAME_FAMILY_INFO}, locale));
		}
		return httpReponseStatus;
	}

	private boolean validateSaveFamilyData(DemographicSaveRequest demographicSaveRequest , HttpReponseStatus httpReponseStatus, Locale locale)throws Exception {
		boolean isValidateFamilyReq = true;
		if(demographicSaveRequest.getCuiid() == null)
			isValidateFamilyReq = false;
		else if(demographicSaveRequest.getFamilyDataResponse() == null)
			isValidateFamilyReq = false;
		if(!isValidateFamilyReq) {
			httpReponseStatus.setStatus(CustomerDataDetailsConstants.FAILED);
			httpReponseStatus.setStatusDesc(messageSource.getMessage("application.demographic.interface.data.error_3", new Object[] {CustomerDataDetailsConstants.INTERFACE_NAME_FAMILY_INFO}, locale));
		}
		return isValidateFamilyReq;
	}
	private void saveFamilyData(DemographicSaveRequest demographicSaveRequest) throws Exception {
		DemographicDetails demographicDetails = demographicReposotory.findByCuiid(demographicSaveRequest.getCuiid());
		if(demographicDetails !=null) {
			FamilyDeatils familyDeatils = demographicDetails.getFamilyDeatils();
			familyDeatils = familyDeatils == null ? new FamilyDeatils() : familyDeatils;
			//set all parent values
			FamilyDataResponse familyDataResponse =  demographicSaveRequest.getFamilyDataResponse();
			familyDeatils.setBaseId(familyDataResponse.getBaseId());
			familyDeatils.setChildrenCount(familyDataResponse.getChildrenCount());
			familyDeatils.setDemographicDetails(demographicDetails);
			familyDeatils.setFamilyCount(familyDataResponse.getFamilyCount());
			familyDeatils.setFamilyHeadId(familyDataResponse.getFamilyHeadId());
			familyDeatils.setSpouseName(familyDataResponse.getSpouseName());
			familyDeatils.setSpouseId(familyDataResponse.getSpouseId());
			familyDeatils.setMarriageDate(familyDataResponse.getMarriageDate());
			populateFamilyRelationDetails(familyDataResponse, familyDeatils);
			familyDetailsRepository.save(familyDeatils);
		}else {
			throw new Exception("No Data Found");
		}
	}
	private void populateFamilyRelationDetails(FamilyDataResponse dataResponse,FamilyDeatils familyDeatils) {
		if(dataResponse.getDetails() != null && !dataResponse.getDetails().isEmpty()) {
			List<FamilyRelationDetails> listFamilyRelationDetails = new ArrayList<>();
			dataResponse.getDetails().forEach(relationVO->{
			 FamilyRelationDetails familyRelationDetails = familyRelationDetailsRepository.findByFamilyDeatilsFamilyIdAndNameLike(familyDeatils.getFamilyId(), relationVO.getName());
			 familyRelationDetails = familyRelationDetails == null ? new  FamilyRelationDetails() : familyRelationDetails;
			 familyRelationDetails.setFamilyDeatils(familyDeatils);
			 familyRelationDetails.setGender(relationVO.getGender());
			 familyRelationDetails.setName(relationVO.getName());
			 familyRelationDetails.setRelation(relationVO.getRelation());
			 familyRelationDetails.setRelationDesc(relationVO.getRelationDesc());
			 listFamilyRelationDetails.add(familyRelationDetails);
			});
			familyDeatils.setFamilyRelationDetails(listFamilyRelationDetails);
		}
	}

}
