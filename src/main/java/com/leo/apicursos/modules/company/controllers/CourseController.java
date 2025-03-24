package com.leo.apicursos.modules.company.controllers;

import com.leo.apicursos.modules.company.DTO.CourseCreateDTO;
import com.leo.apicursos.modules.company.entities.CourseEntity;
import com.leo.apicursos.modules.company.services.CouserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

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

    @GetMapping("/list")
    public ResponseEntity<List<Map<String, Object>>> listCourses() {
        List<Map<String, Object>> courses = couserService.listCourses();
        return ResponseEntity.ok(courses);
    }

    @GetMapping("/list-detailed")
    public ResponseEntity<List<CourseEntity>> listDetailedCourses() {
        List<CourseEntity> courses = couserService.listDetailedCourses();
        return ResponseEntity.ok(courses);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CourseEntity> updateCourse(@PathVariable UUID id, @RequestBody CourseCreateDTO courseDTO) {
        CourseEntity updatedCourse = couserService.updateCourse(id, courseDTO);
        return ResponseEntity.ok(updatedCourse);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable UUID id) {
        couserService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
