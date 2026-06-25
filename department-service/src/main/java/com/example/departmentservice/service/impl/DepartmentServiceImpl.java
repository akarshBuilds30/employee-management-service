package com.example.departmentservice.service.impl;

import com.example.departmentservice.dto.DepartmentRequestDTO;
import com.example.departmentservice.dto.DepartmentResponseDTO;
import com.example.departmentservice.entity.Department;
import com.example.departmentservice.exception.ResourceNotFoundException;
import com.example.departmentservice.mapper.DepartmentMapper;
import com.example.departmentservice.repository.DepartmentRepository;
import com.example.departmentservice.service.IDepartmentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DepartmentServiceImpl implements IDepartmentService {

    private final DepartmentRepository repository;
    private final DepartmentMapper mapper;

    @Override
    public DepartmentResponseDTO create(DepartmentRequestDTO dto) {
        log.info("Creating department: {}", dto.getName());

        Department dept = mapper.toEntity(dto);
        Department saved = repository.save(dept);

        return mapper.toDTO(saved);
    }

    @Override
    public List<DepartmentResponseDTO> getAll() {
        log.info("Fetching all departments");

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public DepartmentResponseDTO getById(Long id) {
        log.info("Fetching department with id: {}", id);

        Department dept = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found"));

        return mapper.toDTO(dept);
    }
}