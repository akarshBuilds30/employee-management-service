package com.example.employeeservice.service;

import com.example.employeeservice.dto.EmployeeRequestDTO;
import com.example.employeeservice.dto.EmployeeResponseDTO;

import java.util.List;

public interface IEmployeeService {
    EmployeeResponseDTO create(EmployeeRequestDTO dto);

    List<EmployeeResponseDTO> getAll();

    EmployeeResponseDTO getById(Long id);

    void delete(Long id);
}