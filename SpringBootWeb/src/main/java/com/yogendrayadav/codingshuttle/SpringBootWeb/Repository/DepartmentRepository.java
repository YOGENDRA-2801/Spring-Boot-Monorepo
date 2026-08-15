package com.yogendrayadav.codingshuttle.SpringBootWeb.Repository;

import com.yogendrayadav.codingshuttle.SpringBootWeb.Entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long>
{

}
