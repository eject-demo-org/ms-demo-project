package com.demo.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.demo.model.dto.FamilyInfoDTO;

public interface FamilyInfoRepository extends MongoRepository<FamilyInfoDTO, UUID> {
	
	FamilyInfoDTO findById(String id);
	

}
