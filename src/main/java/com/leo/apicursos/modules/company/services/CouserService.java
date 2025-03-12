package com.leo.apicursos.modules.company.services;

import com.leo.apicursos.modules.company.DTO.CourseCreateDTO;
import com.leo.apicursos.modules.company.entities.CompanyEntity;
import com.leo.apicursos.modules.company.entities.CourseEntity;
import com.leo.apicursos.modules.company.repositories.CompanyRepository;
import com.leo.apicursos.modules.company.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CouserService {

    @Autowired
    private CourseRepository courseRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public CourseEntity createCourse(CourseCreateDTO courseCreateDTO) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String companyIdStr = authentication.getName();

        CompanyEntity company = companyRepository.findById(UUID.fromString(companyIdStr))
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        CourseEntity courseEntity = new CourseEntity();
        courseEntity.setName(courseCreateDTO.getName());
        courseEntity.setDescription(courseCreateDTO.getDescription());
        courseEntity.setLanguage(courseCreateDTO.getLanguage());
        courseEntity.setLevel(courseCreateDTO.getLevel());
        courseEntity.setPrice(courseCreateDTO.getPrice());
        courseEntity.setCompany(company);

        return courseRepository.save(courseEntity);
    }
}
