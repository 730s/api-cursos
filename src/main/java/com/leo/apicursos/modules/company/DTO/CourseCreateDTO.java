package com.leo.apicursos.modules.company.DTO;

import lombok.Data;

@Data
public class CourseCreateDTO {

    private String name;
    private String description;
    private String language;
    private String level;
    private Double price;
}
