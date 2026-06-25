package com.example.departmentservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DepartmentResponseDTO{
    private Long id;
    private String name;
    }