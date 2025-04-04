package com.leo.apicursos.modules.company.repositories;

import com.leo.apicursos.modules.company.entities.CompanyEntity;
import com.leo.apicursos.modules.company.entities.CourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CourseRepository extends JpaRepository <CourseEntity, UUID> {
    List<CourseEntity> findByCompany(CompanyEntity company);
}
