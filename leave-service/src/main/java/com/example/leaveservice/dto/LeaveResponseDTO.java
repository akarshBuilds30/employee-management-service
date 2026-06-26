package com.example.leaveservice.dto;

import java.time.LocalDate;

public record LeaveResponseDTO(
        Long id,
        Long employeeId,
        LocalDate fromDate,
        LocalDate toDate,
        String reason
) {}