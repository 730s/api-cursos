package com.leo.apicursos.modules.company.DTO;

import lombok.Data;

@Data
public class CompanyCreateDTO {

    private String name;
    private String cnpj;
    private String username;
    private String password;
    private String email;
}
