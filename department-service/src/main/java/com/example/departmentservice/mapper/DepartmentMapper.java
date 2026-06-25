package com.example.departmentservice.mapper;

import com.example.departmentservice.dto.DepartmentRequestDTO;
import com.example.departmentservice.dto.DepartmentResponseDTO;
import com.example.departmentservice.entity.Department;
import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public Department toEntity(DepartmentRequestDTO dto) {
        Department dept = new Department();
        dept.setName(dto.getName());
        return dept;
    }

    public DepartmentResponseDTO toDTO(Department dept) {
        return DepartmentResponseDTO.builder()
                .id(dept.getId())
                .name(dept.getName())
                .build();
    }
}