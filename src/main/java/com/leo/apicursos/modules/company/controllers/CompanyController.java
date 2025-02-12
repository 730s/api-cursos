package com.leo.apicursos.modules.company.controllers;

import com.leo.apicursos.modules.company.DTO.CompanyCreateDTO;
import com.leo.apicursos.modules.company.entities.CompanyEntity;
import com.leo.apicursos.modules.company.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<CompanyEntity> createCompany(@RequestBody CompanyCreateDTO companyCreateDTO){
        CompanyEntity company = companyService.createCompany(companyCreateDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(company);
    }
}
