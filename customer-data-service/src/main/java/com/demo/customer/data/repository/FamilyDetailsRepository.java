package com.demo.customer.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.customer.data.entity.FamilyDeatils;

@Repository
public interface FamilyDetailsRepository extends JpaRepository<FamilyDeatils, Long>{
}
