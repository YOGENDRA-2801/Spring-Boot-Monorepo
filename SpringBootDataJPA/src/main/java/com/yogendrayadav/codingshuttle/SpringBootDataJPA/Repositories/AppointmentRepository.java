package com.yogendrayadav.codingshuttle.SpringBootDataJPA.Repositories;

import com.yogendrayadav.codingshuttle.SpringBootDataJPA.Entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
}