package com.demo.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.DataNotFound;
import com.demo.intf.IPersonalDataService;
import com.demo.model.AddressDataModel;
import com.demo.model.PersonalDataModel;
import com.demo.response.model.PersonalDataResponse;

@RestController
@RequestMapping("/personal")
public class PersonalDataController {

	@Autowired
	private IPersonalDataService personalDataService;


	@GetMapping("/data")
	@ResponseBody
	public ResponseEntity<PersonalDataResponse> getPersonalData(@RequestParam("id") String uid) {


		System.out.println("Request received-:"+uid);

		PersonalDataModel dataModel = personalDataService.getPersonalData(uid);
		System.out.println("Datamodel:"+dataModel);
		if(dataModel == null) {
			throw new DataNotFound("ID does not exit");
		}

		PersonalDataResponse response = new PersonalDataResponse(HttpStatus.FOUND, "Data Found", dataModel);

		Set<AddressDataModel> addresses = personalDataService.getAddressList(uid);
		
		response.setAddress(addresses);
		
		System.out.println("Response:"+response);

		return new ResponseEntity<PersonalDataResponse>(response, HttpStatus.FOUND);
		//return ResponseEntity.ok(response);
	}

}
