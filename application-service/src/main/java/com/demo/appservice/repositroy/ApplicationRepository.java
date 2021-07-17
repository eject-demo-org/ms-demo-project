package com.demo.appservice.repositroy;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.appservice.entity.Application;


@Repository
public interface ApplicationRepository extends JpaRepository<Application, Long>{

	Application findByApplicantDetailsCuiidAndApplicationNumber(String cuiid , Long applicationNumber);
}
