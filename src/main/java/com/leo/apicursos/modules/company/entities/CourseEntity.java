package com.leo.apicursos.modules.company.entities;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity(name = "course")
@Data
public class CourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private String name;
    private String description;
    private String language;
    private String level;
    private double price;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
