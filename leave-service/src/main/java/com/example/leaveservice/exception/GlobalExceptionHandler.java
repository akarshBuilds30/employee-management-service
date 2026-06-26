package com.example.leaveservice.exception;

import com.example.employeeservice.exception.ResourceNotFoundException;
import com.example.leaveservice.dto.ErrorResponseDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponseDTO> handleResourceNotFoundException(Exception exception, WebRequest webRequest) {
        ErrorResponseDTO ErrorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false), HttpStatus.NOT_FOUND,
                exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(ErrorResponseDTO, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponseDTO> handleBadRequestException(Exception exception, WebRequest webRequest) {
        ErrorResponseDTO ErrorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false), HttpStatus.BAD_REQUEST,
                exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(ErrorResponseDTO, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDTO> handleException(Exception exception, WebRequest webRequest) {
        ErrorResponseDTO ErrorResponseDTO = new ErrorResponseDTO(
                webRequest.getDescription(false), HttpStatus.INTERNAL_SERVER_ERROR,
                exception.getMessage(), LocalDateTime.now());
        return new ResponseEntity<>(ErrorResponseDTO, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}