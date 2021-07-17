package com.demo.customer.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.customer.data.entity.DemographicDetails;

@Repository
public interface DemographicReposotory extends JpaRepository<DemographicDetails, Long> {

	DemographicDetails findByApplicationNumberAndCuiid(Long applicationNumber, String cuiid);

}
