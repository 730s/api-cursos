package com.leo.apicursos.modules.company.services;

import com.auth0.jwt.algorithms.Algorithm;
import com.leo.apicursos.modules.company.DTO.AuthCompanyRequestDTO;
import com.leo.apicursos.modules.company.DTO.AuthCompanyResponseDTO;
import com.leo.apicursos.modules.company.entities.CompanyEntity;
import com.leo.apicursos.modules.company.repositories.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;


@Service
public class AuthService {

    @Value("${security.token.secret}")
    private String secretKey;

    @Autowired
    private CompanyRepository companyRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(CompanyRepository companyRepository, PasswordEncoder passwordEncoder) {
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AuthCompanyResponseDTO authenticate(AuthCompanyRequestDTO authCompanyRequestDTO){
        Optional<CompanyEntity> companyOpt = companyRepository.findByUsername(authCompanyRequestDTO.username());
        if (companyOpt.isEmpty()){
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        CompanyEntity company = companyOpt.get();

        if (!passwordEncoder.matches(authCompanyRequestDTO.password(), company.getPassword())){
            throw new RuntimeException("Usuário ou senha inválidos");
        }

        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        var expiresIn = Instant.now().plus(Duration.ofMinutes(10));
        var token = com.auth0.jwt.JWT.create()
                .withIssuer("apicursos")
                .withSubject(company.getId().toString())
                .withExpiresAt(expiresIn)
                .sign(algorithm);

        return new AuthCompanyResponseDTO(token, expiresIn.toEpochMilli());
    }

}
