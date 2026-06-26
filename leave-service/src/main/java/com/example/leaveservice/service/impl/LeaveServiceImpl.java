package com.example.leaveservice.service.impl;

import com.example.employeeservice.exception.ResourceNotFoundException;
import com.example.leaveservice.dto.EmployeeDTO;
import com.example.leaveservice.dto.LeaveRequestDTO;
import com.example.leaveservice.dto.LeaveResponseDTO;
import com.example.leaveservice.entity.Leave;
import com.example.leaveservice.mapper.LeaveMapper;
import com.example.leaveservice.repository.LeaveRepository;
import com.example.leaveservice.service.ILeaveService;
import com.example.leaveservice.service.client.EmployeeClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LeaveServiceImpl implements ILeaveService {

    private final LeaveRepository repository;
    private final LeaveMapper mapper;
    private final EmployeeClient employeeClient;

    public LeaveServiceImpl(LeaveRepository repository, LeaveMapper mapper, EmployeeClient employeeClient) {
        this.repository = repository;
        this.mapper = mapper;
        this.employeeClient = employeeClient;
    }

    @Override
    public LeaveResponseDTO applyLeave(LeaveRequestDTO dto) {

        log.info("Applying leave for employee: {}", dto.getEmployeeId());

        EmployeeDTO employee;
        try {
            employee = employeeClient.getEmployeeById(dto.getEmployeeId());
        } catch (Exception ex) {
            log.error("Employee not found with id: {}", dto.getEmployeeId());
            throw new ResourceNotFoundException("Employee not found with id: " + dto.getEmployeeId());
        }

        if (dto.getFromDate().isAfter(dto.getToDate())) {
            throw new IllegalArgumentException("From date cannot be after To date");
        }

        Leave leave = mapper.toEntity(dto);
        Leave saved = repository.save(leave);

        return mapper.toDTO(saved);
    }

    @Override
    public List<LeaveResponseDTO> getAllLeaves() {
        log.info("Fetching all leaves");

        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Override
    public List<LeaveResponseDTO> getLeavesByEmployee(Long employeeId) {
        log.info("Fetching leaves for employee: {}", employeeId);
        return repository.findByEmployeeId(employeeId)
                .stream()
                .map(mapper::toDTO)
                .toList();
    }
}