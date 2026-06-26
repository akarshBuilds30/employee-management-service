package com.example.leaveservice.mapper;

import com.example.leaveservice.dto.LeaveRequestDTO;
import com.example.leaveservice.dto.LeaveResponseDTO;
import com.example.leaveservice.entity.Leave;
import org.springframework.stereotype.Component;

@Component
public class LeaveMapper {

    public Leave toEntity(LeaveRequestDTO dto) {
        Leave leave = new Leave();
        leave.setEmployeeId(dto.getEmployeeId());
        leave.setFromDate(dto.getFromDate());
        leave.setToDate(dto.getToDate());
        leave.setReason(dto.getReason());
        return leave;
    }

    public LeaveResponseDTO toDTO(Leave leave) {
        return new LeaveResponseDTO(
                leave.getId(),
                leave.getEmployeeId(),
                leave.getFromDate(),
                leave.getToDate(),
                leave.getReason()
        );
    }
}