package com.demo.service.impl;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.demo.model.data.base.to.FamilyInfoDataBaseTO;
import com.demo.model.dto.FamilyInfoDTO;
import com.demo.model.mapper.FamilyInfoMapper;
import com.demo.model.to.InterfaceResponseTO;
import com.demo.repository.FamilyInfoRepository;
import com.demo.service.InterfaceCallService;

@Service
@Qualifier("familyInfoService")
public class FamilyInfoServiceImpl implements InterfaceCallService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	FamilyInfoRepository repository;



	private final String url = "http://localhost:8001/family/data";

	@Override
	public Boolean call(String requestId) {
		
		return null;
	}

	@Override
	public InterfaceResponseTO callInterface(String requestId) {

		System.out.println("URL" + url);
		UriComponentsBuilder paramURI = UriComponentsBuilder.fromUriString(url)
				.queryParam("id", requestId);

		System.out.println(paramURI.build().toUri());

		FamilyInfoDataBaseTO family = restTemplate.getForObject(paramURI.toUriString(), FamilyInfoDataBaseTO.class);

		FamilyInfoDTO dto = repository.findById(requestId);

		if(family != null) {
			if(dto == null) {
				dto = FamilyInfoMapper.INSTANCE.mapFamilyInfoDataTO2DTO(family.getFamilyData());
				dto.setUuid(UUID.randomUUID());
				repository.insert(dto);
			}else {
				FamilyInfoDTO newDto = FamilyInfoMapper.INSTANCE.mapFamilyInfoDataTO2DTO(family.getFamilyData());
				newDto.setUuid(dto.getUuid());
				dto = repository.save(newDto);
			}			
			return FamilyInfoMapper.INSTANCE.mapFamilyInfoDTO2TO(dto);
		}else {
			if(dto!=null) {
				return FamilyInfoMapper.INSTANCE.mapFamilyInfoDTO2TO(dto);
			}
		}

		return null;
	}

	@Override
	public InterfaceResponseTO getInfoOnID(String requestId) {
		
		FamilyInfoDTO dto = repository.findById(requestId);
		if(dto==null)
			return null;
		
		return FamilyInfoMapper.INSTANCE.mapFamilyInfoDTO2TO(dto);
	}



}
