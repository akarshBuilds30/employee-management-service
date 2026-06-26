package com.example.leaveservice.service.client;

import com.example.leaveservice.dto.EmployeeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service", url = "http://localhost:8081")
public interface EmployeeClient {

    @GetMapping("/employees/{id}")
    EmployeeDTO getEmployeeById(@PathVariable Long id);
}