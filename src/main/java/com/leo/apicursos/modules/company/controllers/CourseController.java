package com.leo.apicursos.modules.company.controllers;

import com.leo.apicursos.modules.company.DTO.CourseCreateDTO;
import com.leo.apicursos.modules.company.entities.CourseEntity;
import com.leo.apicursos.modules.company.services.CouserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CouserService couserService;

    @PostMapping("/create")
    public ResponseEntity<CourseEntity> createCourse(@RequestBody CourseCreateDTO courseCreateDTO){
        CourseEntity course = couserService.createCourse(courseCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(course);
    }
}
