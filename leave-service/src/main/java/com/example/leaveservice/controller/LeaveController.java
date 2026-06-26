package com.example.leaveservice.controller;

import com.example.leaveservice.dto.LeaveRequestDTO;
import com.example.leaveservice.dto.LeaveResponseDTO;
import com.example.leaveservice.service.impl.LeaveServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leaves")
@RequiredArgsConstructor
@Tag(name = "Leave APIs")
public class LeaveController {

    private final LeaveServiceImpl service;

    @PostMapping
    @Operation(summary = "Apply leave")
    public ResponseEntity<LeaveResponseDTO> apply(
            @Valid @RequestBody LeaveRequestDTO dto) {

        return ResponseEntity.ok(service.applyLeave(dto));
    }

    @GetMapping
    public ResponseEntity<List<LeaveResponseDTO>> getAll() {
        return ResponseEntity.ok(service.getAllLeaves());
    }

    @GetMapping("/employee/{employeeId}")
    public ResponseEntity<List<LeaveResponseDTO>> getByEmployee(
            @PathVariable Long employeeId) {

        return ResponseEntity.ok(service.getLeavesByEmployee(employeeId));
    }
}