package com.demo.service.impl;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.model.InterfaceRequestModel;
import com.demo.model.data.base.to.PersonalInfoDataBaseTO;
import com.demo.model.dto.AddressDTO;
import com.demo.model.dto.PersonalInfoDTO;
import com.demo.model.mapper.AddressMapper;
import com.demo.model.mapper.PersonInfoMapper;
import com.demo.model.to.AddressTO;
import com.demo.model.to.InterfaceResponseTO;
import com.demo.model.to.PersonalInfoTO;
import com.demo.repository.PersonalInfoRepository;
import com.demo.service.InterfaceCallService;

@Service
@Qualifier("personalInfoService")
public class PersonalInfoServiceImpl implements InterfaceCallService {


	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	PersonalInfoRepository repository;

	private final String url = "http://localhost:8001/personal/data";

	@Override
	public Boolean call(String requestId) {


		System.out.println("URL" + url);
		UriComponentsBuilder paramURI = UriComponentsBuilder.fromUriString(url)
				.queryParam("id", requestId);

		System.out.println(paramURI.build().toUri());
		PersonalInfoDataBaseTO emp = restTemplate.getForObject(paramURI.toUriString(), PersonalInfoDataBaseTO.class);
		//restTemplate.getForEntity(paramURI.toUriString(, ));

		if(emp!=null && emp.getResponseCode().equals(HttpStatus.FOUND)) {
			System.out.println("inside save");

			PersonalInfoDTO personalDTO= PersonInfoMapper.INSTANCE.mapPersonalInfoDataTO2DTO(emp.getPersonalDataTO());
			personalDTO.setUniqueId(requestId);
			personalDTO.setId(UUID.randomUUID());
			
			System.out.println(emp.getAddressDataTO());
			if(emp.getAddressDataTO()!=null) {
				Set<AddressDTO> dto = new LinkedHashSet<>();
				emp.getAddressDataTO().forEach(data -> dto.add(AddressMapper.INSTANCE.mapAddressDataTO2DTO(data)));
				personalDTO.setAddresses(dto);
			}

			
			repository.save(personalDTO);			
		}
		System.out.println("saved");


		return true;
	}

	@Override
	public InterfaceResponseTO getInfoOnID(String requestId) {

		try {
			PersonalInfoDTO dto = repository.findByUniqueId(requestId);
			if(dto!=null) {
				
				PersonalInfoTO to = PersonInfoMapper.INSTANCE.mapPersonalInfoDTO2TO(dto);
				
				Set<AddressTO> addresses = new LinkedHashSet<>();
				
				dto.getAddresses().forEach(data -> addresses.add(AddressMapper.INSTANCE.mapAddressDTO2TO(data)));
				
				to.setAddresses(addresses);
				
				return to;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public InterfaceResponseTO callInterface(String requestId) {

		return null;
	}

}
