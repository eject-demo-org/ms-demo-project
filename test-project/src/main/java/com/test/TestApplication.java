package com.test;

import java.math.BigDecimal;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.mapper.ModelMapper;
import com.test.models.CopyModel;
import com.test.models.OriginalChildModel;
import com.test.models.OriginalModel;
import com.test.models.OriginalSetModel;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
		
		OriginalChildModel cm = new OriginalChildModel("child name", new Date());
		
		Set<OriginalSetModel> setModel = new LinkedHashSet<>();
		
		for(int i=1; i<6; i++) {			
			setModel.add(new OriginalSetModel("aa"+i, (i*(4*56))));
		}
		
		OriginalModel o = new OriginalModel("anc", 25, "abc@xyz.com", new BigDecimal(500), cm, setModel);
		System.out.println(o);
		CopyModel c = ModelMapper.INSTANCE.mapOriginal2Copy(o);
		
		System.out.println(c);
		
	}

}
