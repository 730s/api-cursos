package com.leo.apicursos.modules.company.services;

import com.leo.apicursos.modules.company.DTO.CourseCreateDTO;
import com.leo.apicursos.modules.company.entities.CompanyEntity;
import com.leo.apicursos.modules.company.entities.CourseEntity;
import com.leo.apicursos.modules.company.repositories.CompanyRepository;
import com.leo.apicursos.modules.company.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

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

    public List<Map<String, Object>> listCourses() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String companyIdStr = authentication.getName();

        CompanyEntity company = companyRepository.findById(UUID.fromString(companyIdStr))
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));

        List<CourseEntity> courses = courseRepository.findByCompany(company);
        return courses.stream()
                .map(course -> {
                    Map<String, Object> map = new HashMap<>();
                    map.put("name", course.getName());
                    map.put("price", course.getPrice());
                    map.put("language", course.getLanguage());
                    return map;
                })
                .collect(Collectors.toList());
    }

    public List<CourseEntity> listDetailedCourses() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String companyIdStr = authentication.getName();
        CompanyEntity company = companyRepository.findById(UUID.fromString(companyIdStr))
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        return courseRepository.findByCompany(company);
    }

    public CourseEntity updateCourse(UUID id, CourseCreateDTO courseDTO) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String companyIdStr = authentication.getName();
        CompanyEntity company = companyRepository.findById(UUID.fromString(companyIdStr))
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        if (!course.getCompany().getId().toString().equals(companyIdStr)) {
            throw new RuntimeException("Você não tem permissão para atualizar este curso.");
        }
        course.setName(courseDTO.getName());
        course.setDescription(courseDTO.getDescription());
        course.setLanguage(courseDTO.getLanguage());
        course.setLevel(courseDTO.getLevel());
        course.setPrice(courseDTO.getPrice());
        return courseRepository.save(course);
    }

    public void deleteCourse(UUID id) {
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        String companyIdStr = authentication.getName();
        CompanyEntity company = companyRepository.findById(UUID.fromString(companyIdStr))
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
        CourseEntity course = courseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        if (!course.getCompany().getId().toString().equals(companyIdStr)) {
            throw new RuntimeException("Você não tem permissão para deletar este curso.");
        }
        courseRepository.delete(course);
    }
}
