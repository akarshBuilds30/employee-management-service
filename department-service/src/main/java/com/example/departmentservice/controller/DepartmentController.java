package com.example.departmentservice.controller;

import com.example.departmentservice.dto.DepartmentRequestDTO;
import com.example.departmentservice.dto.DepartmentResponseDTO;
import com.example.departmentservice.service.impl.DepartmentServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
@RequiredArgsConstructor
@Tag(name = "Department APIs")
public class DepartmentController {

    private final DepartmentServiceImpl service;

    @PostMapping
    @Operation(summary = "Create department")
    public ResponseEntity<DepartmentResponseDTO> create(
            @Valid @RequestBody DepartmentRequestDTO dto) {

        return ResponseEntity.ok(service.create(dto));
    }

    @GetMapping
    public ResponseEntity<List<DepartmentResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.getById(id));
    }
}