package com.yogendrayadav.codingshuttle.SpringBootDataJPA.Repositories;

import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.InsuranceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Long> {
}