package com.leo.apicursos.modules.company.controllers;

import com.leo.apicursos.modules.company.DTO.AuthCompanyRequestDTO;
import com.leo.apicursos.modules.company.DTO.AuthCompanyResponseDTO;
import com.leo.apicursos.modules.company.services.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/company")
public class AuthCompanyController {

    private final AuthService authService;

    public AuthCompanyController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    public ResponseEntity<AuthCompanyResponseDTO> authenticate(@RequestBody AuthCompanyRequestDTO request) {
        AuthCompanyResponseDTO response = authService.authenticate(request);
        return ResponseEntity.ok(response);
    }
}
