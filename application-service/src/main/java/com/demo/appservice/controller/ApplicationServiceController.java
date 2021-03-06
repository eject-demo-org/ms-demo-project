package com.demo.appservice.controller;

import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.appservice.service.ApplicationService;
import com.demo.appservice.vo.ApplicationCreationRequest;
import com.demo.appservice.vo.ApplicationCreationResponse;
import com.demo.appservice.vo.HttpReponseStatus;

@RestController
@RequestMapping("/application")
public class ApplicationServiceController {

	@Autowired
	private ApplicationService applicationService;
	
	@PostMapping("/addApplication")
	public ResponseEntity<ApplicationCreationResponse> addApplication(@RequestBody ApplicationCreationRequest application , Locale locale) {
		 return new ResponseEntity<ApplicationCreationResponse>(applicationService.createApplication(application ,locale)
				,HttpStatus.OK);
	}
	
	@GetMapping("/verifyCustomerId/{cuiid}")
	public ResponseEntity<String> verifyCustomerId(@PathVariable("cuiid") String cuiid , Locale locale){
		String isVerified = applicationService.verifyCustomerId(cuiid ,  locale ,null);
		return new ResponseEntity<String>(isVerified,HttpStatus.OK);
	}
	//call family and save its data
	@PostMapping("/callAndSave/{interfaceName}")
	public ResponseEntity<HttpReponseStatus> callAndSaveFamilyData(@RequestBody ApplicationCreationRequest application ,@PathVariable String interfaceName, Locale locale) {
		 return new ResponseEntity<HttpReponseStatus>(applicationService.callAndSaveByInterface(application ,interfaceName,locale)
				,HttpStatus.OK);
	}
	
	
}
