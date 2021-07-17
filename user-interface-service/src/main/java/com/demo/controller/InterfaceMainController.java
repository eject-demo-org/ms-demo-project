package com.demo.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.exception.DataNotFound;
import com.demo.factory.InterfaceCallFactory;
import com.demo.model.InterfaceRequestModel;
import com.demo.model.InterfaceResponseModel;
import com.demo.model.to.InterfaceResponseTO;
import com.demo.service.InterfaceCallService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/interface")
@Slf4j
public class InterfaceMainController {
		
	@Autowired
	private InterfaceCallFactory interfaceFactory;
	
	@PostMapping("/call")
	@ResponseBody
	public String call(@RequestBody InterfaceRequestModel model) {
		log.debug("Start");
		InterfaceCallService service = interfaceFactory.getInterfaceService(model.getInterfaceName());
		service.callInterface(model.getUid());
		System.out.println("End");		
		return "Created";
	}	
	
	@PostMapping("/callInterface")
	@ResponseBody
	public InterfaceResponseModel callInterface(@RequestBody InterfaceRequestModel request) {
		
		InterfaceCallService service = interfaceFactory.getInterfaceService(request.getInterfaceName());
		
		InterfaceResponseTO responseTO = service.callInterface(request.getUid());
		
		if(responseTO==null) {
			throw new DataNotFound("Family Data Not Found.");
		}
		
		return new InterfaceResponseModel(HttpStatus.FOUND, "Data Found", responseTO);
	}
	
	@GetMapping("/getInterfaceData")
	@ResponseBody
	public InterfaceResponseModel getInterfaceData(@RequestParam String interfaceName, @RequestParam String uid) {
		
		InterfaceResponseTO respTO = interfaceFactory.getInterfaceService(interfaceName).getInfoOnID(uid);
		if((respTO !=null)) {
			return new InterfaceResponseModel(HttpStatus.FOUND, "Data Found", respTO);
		}
		
		return new InterfaceResponseModel(HttpStatus.NOT_FOUND, "No Data", null);
	}

}
