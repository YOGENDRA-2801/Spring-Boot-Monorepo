package com.yogendrayadav.codingshuttle.SpringBootWeb.Repository;

import com.yogendrayadav.codingshuttle.SpringBootWeb.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
