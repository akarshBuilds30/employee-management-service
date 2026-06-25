package com.example.employeeservice.service.impl;

import com.example.employeeservice.dto.EmployeeRequestDTO;
import com.example.employeeservice.dto.EmployeeResponseDTO;
import com.example.employeeservice.entity.Employee;
import com.example.employeeservice.exception.ResourceNotFoundException;
import com.example.employeeservice.mapper.EmployeeMapper;
import com.example.employeeservice.repository.EmployeeRepository;
import com.example.employeeservice.service.IEmployeeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmployeeServiceImpl implements IEmployeeService {

    private final EmployeeRepository repository;
    private final EmployeeMapper mapper;

    public EmployeeResponseDTO create(@Valid EmployeeRequestDTO dto) {
        log.info("Creating employee: {}", dto.getName());

        Employee emp = mapper.toEntity(dto);
        Employee saved = repository.save(emp);

        return mapper.toDTO(saved);
    }

    @Override
    public List<EmployeeResponseDTO> getAll() {
        log.info("Fetching all employees");

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public EmployeeResponseDTO getById(Long id) {
        log.info("Fetching employee with id: {}", id);

        Employee emp = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found"));

        return mapper.toDTO(emp);
    }

    @Override
    public void delete(Long id) {
        log.info("Deleting employee with id: {}", id);

        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Employee not found");
        }

        repository.deleteById(id);
    }
}