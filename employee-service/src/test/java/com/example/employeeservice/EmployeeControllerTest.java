package com.example.employeeservice;

import com.example.employeeservice.controller.EmployeeController;
import com.example.employeeservice.dto.EmployeeRequestDTO;
import com.example.employeeservice.dto.EmployeeResponseDTO;
import com.example.employeeservice.exception.ResourceNotFoundException;
import com.example.employeeservice.service.impl.EmployeeServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeServiceImpl service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateEmployee() throws Exception {

        EmployeeRequestDTO request = EmployeeRequestDTO.builder()
                .name("akarsh")
                .email("akarsh@mail.com")
                .build();
        EmployeeResponseDTO response = EmployeeResponseDTO.builder()
                .id(1L)
                .name("akarsh")
                .email("akarsh@mail.com")
                .build();

        when(service.create(any(EmployeeRequestDTO.class))).thenReturn(response);

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("akarsh"));
    }

    @Test
    void testCreateEmployee_validationFail() throws Exception {

        EmployeeRequestDTO request = EmployeeRequestDTO.builder()
                .name("")
                .email("")
                .build(); // invalid

        mockMvc.perform(post("/employees")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest());
    }

    @Test
    void testGetAllEmployees() throws Exception {

        List<EmployeeResponseDTO> list = List.of(
                EmployeeResponseDTO.builder()
                        .id(1L)
                        .name("akarsh")
                        .email("akarsh@mail.com")
                        .build(),

                EmployeeResponseDTO.builder()
                        .id(2L)
                        .name("John")
                        .email("john@mail.com")
                        .build()
        );

        when(service.getAll()).thenReturn(list);

        mockMvc.perform(get("/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2));
    }

    @Test
    void testGetEmployeeById() throws Exception {

        EmployeeResponseDTO response = EmployeeResponseDTO.builder()
                .id(1L)
                .name("akarsh")
                .email("akarsh@mail.com")
                .build();

        when(service.getById(1L)).thenReturn(response);

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("akarsh"));
    }

    @Test
    void testGetEmployeeById_notFound() throws Exception {

        when(service.getById(1L))
                .thenThrow(new ResourceNotFoundException("Employee not found"));

        mockMvc.perform(get("/employees/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    void testDeleteEmployee() throws Exception {

        doNothing().when(service).delete(1L);

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void testDeleteEmployee_notFound() throws Exception {

        doThrow(new ResourceNotFoundException("Employee not found"))
                .when(service).delete(1L);

        mockMvc.perform(delete("/employees/1"))
                .andExpect(status().isNotFound());
    }
}