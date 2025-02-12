package com.leo.apicursos.modules.company.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "company")
@Data
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @NotNull
    private String name;
    @Length (min = 14, max = 14, message = "Informe um CNPJ válido")
    private String cnpj;
    @Pattern(regexp = "\\S+", message = "O campo não deve conter espaços.")
    private String username;
    @Length(min = 10, max = 100, message = "A senha deve conter entre 10 a 100 caracteres.")
    private String password;
    @Email(message = "O campo deve conter um e-mail válido.")
    private String email;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
