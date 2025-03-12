package com.leo.apicursos.modules.company.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.leo.apicursos.modules.company.DTO.AuthCompanyRequestDTO;
import com.leo.apicursos.modules.company.DTO.AuthCompanyResponseDTO;
import com.leo.apicursos.modules.company.entities.CompanyEntity;
import com.leo.apicursos.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;

@Service
public class AuthService {

    @Value("${security.token.secret}")
    private String secretKey;

    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthCompanyResponseDTO authenticate(AuthCompanyRequestDTO request) {
        CompanyEntity company = companyRepository.findByUsername(request.username())
                .orElseThrow(() -> new BadCredentialsException("Usuário ou senha inválidos"));

        if (!passwordEncoder.matches(request.password(), company.getPassword())) {
            throw new BadCredentialsException("Usuário ou senha inválidos");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        Instant expiresAt = Instant.now().plus(Duration.ofMinutes(10));
        String token = JWT.create()
                .withIssuer("apicursos")
                .withSubject(company.getId().toString())
                .withExpiresAt(Date.from(expiresAt))
                .sign(algorithm);

        return new AuthCompanyResponseDTO(token, expiresAt.toEpochMilli());
        }
    }