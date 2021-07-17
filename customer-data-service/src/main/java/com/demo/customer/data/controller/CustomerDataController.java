package com.demo.customer.data.controller;

import java.util.Locale;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.customer.data.entity.DemographicDetails;
import com.demo.customer.data.service.CustomerDataDetailsService;
import com.demo.customer.data.vo.DemographicSaveRequest;
import com.demo.customer.data.vo.HttpReponseStatus;

@RestController
@RequestMapping("/customerData")
public class CustomerDataController {

	private CustomerDataDetailsService customerDataDetailsService;
	
	public CustomerDataController(CustomerDataDetailsService customerDataDetailsService) {
		super();
		this.customerDataDetailsService = customerDataDetailsService;
	}


	@PostMapping("/getDemographicData")
	public ResponseEntity<DemographicDetails> getDemographicData(@RequestBody DemographicDetails demographicDetails,
			Locale locale) {
		return new ResponseEntity<DemographicDetails>(
				customerDataDetailsService.getDemographicDetailsByCuiidAndApplicationNumber(demographicDetails, locale),
				HttpStatus.OK);
	}

	@PostMapping("/saveDemographicData")
	public ResponseEntity<HttpReponseStatus> saveDemographicData(
			@RequestBody DemographicSaveRequest demographicSaveRequest, Locale locale) {
		return new ResponseEntity<HttpReponseStatus>(
				customerDataDetailsService.saveDemographicDetails(demographicSaveRequest, locale), HttpStatus.OK);
	}

	
}
