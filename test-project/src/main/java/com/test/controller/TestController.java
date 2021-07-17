package com.test.controller;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.models.CopyChildModel;
import com.test.models.CopyModel;
import com.test.models.CopySetModel;

@RestController
@RequestMapping("/test")
public class TestController {

	@GetMapping("/getName")
	public String getString(@RequestParam("name") String name) {
		System.out.println(name);
		return "Mr. "+name;

	}

	@GetMapping("/getCopyModel")
	@ResponseBody
	public CopyModel getModel(@RequestParam String name) {

		CopyModel ret = new CopyModel();
		ret.setName("Mr. "+name);
		ret.setAge(25);
		ret.setMail("mail@mail.com");
		

		CopyChildModel child = new CopyChildModel();
		child.setChildDOB(new Date());
		child.setChildName("child Name");


		Set<CopySetModel> setModel = new HashSet<>();
		for(int i=1; i<6; i++) {			
			CopySetModel obj = new CopySetModel();
			obj.setNameSetC("copy name - "+i);
			obj.setNumSetC(i*13*7L);
			setModel.add(obj);
		}
		
		ret.setChild(child);
		ret.setSetC(setModel);
		
		return ret;
	}
}
