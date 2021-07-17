package com.demo.repository;

import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.demo.model.dto.PersonalInfoDTO;

@Repository
public interface PersonalInfoRepository extends MongoRepository<PersonalInfoDTO, UUID> {
	
	PersonalInfoDTO findByUniqueId(String uniqueId);
	

}
