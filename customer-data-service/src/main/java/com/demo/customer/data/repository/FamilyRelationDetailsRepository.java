package com.demo.customer.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.customer.data.entity.FamilyRelationDetails;

@Repository
public interface FamilyRelationDetailsRepository extends JpaRepository<FamilyRelationDetails, Long> {
	FamilyRelationDetails findByFamilyDeatilsFamilyIdAndNameLike(Long familyId, String name);
}
