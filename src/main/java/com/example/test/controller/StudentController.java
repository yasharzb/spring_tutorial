package com.example.test.controller;

import com.example.test.model.dto.StudentDTO;
import com.example.test.service.DBService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class StudentController {
    @Autowired
    private DBService dbService;

    @PostMapping("/register")
    public void register(@RequestBody StudentDTO studentDTO) {
        dbService.register(studentDTO.getId(), studentDTO.getName(), studentDTO.getStatus());
    }

    @GetMapping("/get_info")
    public StudentDTO getInfo(@RequestParam(name = "name") String name) {
        return dbService.getInfo(name);
    }
}
