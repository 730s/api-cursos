package com.leo.apicursos.modules.company.services;

import com.leo.apicursos.modules.company.DTO.CompanyCreateDTO;
import com.leo.apicursos.modules.company.entities.CompanyEntity;
import com.leo.apicursos.modules.company.repositories.CompanyRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    public CompanyService (CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public CompanyEntity createCompany(CompanyCreateDTO companyCreateDTO){
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setName(companyCreateDTO.getName());
        companyEntity.setCnpj(companyCreateDTO.getCnpj());
        companyEntity.setUsername(companyCreateDTO.getUsername());
        companyEntity.setPassword(passwordEncoder.encode(companyCreateDTO.getPassword()));
        companyEntity.setEmail(companyCreateDTO.getEmail());

        return companyRepository.save(companyEntity);
    }
}
