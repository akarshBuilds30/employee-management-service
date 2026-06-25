package com.example.employeeservice.mapper;

import com.example.employeeservice.dto.EmployeeRequestDTO;
import com.example.employeeservice.dto.EmployeeResponseDTO;
import com.example.employeeservice.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public Employee toEntity(EmployeeRequestDTO dto) {
        Employee emp = new Employee();
        emp.setName(dto.getName());
        emp.setEmail(dto.getEmail());
        return emp;
    }

    public EmployeeResponseDTO toDTO(Employee emp) {
        return EmployeeResponseDTO.builder()
                .id(emp.getId())
                .name(emp.getName())
                .email(emp.getEmail())
                .build();
    }
}