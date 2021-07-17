package com.demo.customer.data.service;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.demo.customer.data.entity.DemographicDetails;
import com.demo.customer.data.vo.DemographicSaveRequest;
import com.demo.customer.data.vo.HttpReponseStatus;

@Component
@Qualifier("customerDataDetailsService")
public interface CustomerDataDetailsService {

	DemographicDetails getDemographicDetailsByCuiidAndApplicationNumber(DemographicDetails demographicDetails,
			Locale locale);

	HttpReponseStatus saveDemographicDetails(DemographicSaveRequest demographicSaveRequest, Locale locale);
}
