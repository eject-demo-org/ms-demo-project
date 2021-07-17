package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.DataNotFound;
import com.demo.intf.IFamilyDataService;
import com.demo.model.FamilyDataModel;
import com.demo.response.model.FamilyDataReponse;

@RestController
@RequestMapping("/family")
public class FamilyDataController {
	
	@Autowired
	private IFamilyDataService familyService;
	
	@GetMapping("/data")
	@ResponseBody
	public ResponseEntity<FamilyDataReponse> getFamilyData(@RequestParam("id") String uid) {
		
		FamilyDataModel data = familyService.getFamilyData(uid);
		
		if(data == null)
			throw new DataNotFound("No Family Data");
		
		data.setDetails(familyService.getFamilyDetails(uid));
		
		return new ResponseEntity<FamilyDataReponse>(new FamilyDataReponse(HttpStatus.FOUND, "Data Found", data), HttpStatus.FOUND);
	}	

}
