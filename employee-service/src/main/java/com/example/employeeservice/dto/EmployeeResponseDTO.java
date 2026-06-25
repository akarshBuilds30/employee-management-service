package com.example.employeeservice.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class EmployeeResponseDTO {

    private Long id;
    private String name;
    private String email;
}