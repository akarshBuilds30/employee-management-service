
package com.example.employeeservice.controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class MainController {
    @GetMapping("/health")
    public String health() {
        return "OK";
    }
}
