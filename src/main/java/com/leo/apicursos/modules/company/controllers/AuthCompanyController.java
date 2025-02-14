package com.leo.apicursos.modules.company.controllers;

import com.leo.apicursos.modules.company.DTO.AuthCompanyRequestDTO;
import com.leo.apicursos.modules.company.DTO.AuthCompanyResponseDTO;
import com.leo.apicursos.modules.company.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

    @Autowired
    private AuthService authService;

    @PostMapping("/auth")
    public ResponseEntity<AuthCompanyResponseDTO> auth (@RequestBody AuthCompanyRequestDTO authCompanyRequestDTO){
        var authResponse = authService.authenticate(authCompanyRequestDTO);
        return ResponseEntity.ok(authResponse);
    }
}
