package com.example.departmentservice.service;

import com.example.departmentservice.dto.DepartmentRequestDTO;
import com.example.departmentservice.dto.DepartmentResponseDTO;

import java.util.List;

public interface IDepartmentService {
    DepartmentResponseDTO create(DepartmentRequestDTO dto);

    List<DepartmentResponseDTO> getAll();

    DepartmentResponseDTO getById(Long id);
}