package com.example.leaveservice.service;

import com.example.leaveservice.dto.LeaveRequestDTO;
import com.example.leaveservice.dto.LeaveResponseDTO;

import java.util.List;

public interface ILeaveService {

    LeaveResponseDTO applyLeave(LeaveRequestDTO dto);

    List<LeaveResponseDTO> getAllLeaves();

    List<LeaveResponseDTO> getLeavesByEmployee(Long employeeId);
}