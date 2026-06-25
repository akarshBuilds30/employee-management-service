package com.example.departmentservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartmentRequestDTO {

    @NotBlank
    private String name;
}