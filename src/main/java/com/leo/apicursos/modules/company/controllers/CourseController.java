package com.leo.apicursos.modules.company.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
public class CourseController {

    @GetMapping("/teste")
    public String retornoTeste(){
        return "Testado";
    }
}
