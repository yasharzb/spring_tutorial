package com.example.test.service;

import com.example.test.model.dto.StudentDTO;
import com.example.test.model.entities.Student;
import com.example.test.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {
    @Autowired
    private StudentRepository studentRepository;

    public void register(int id, String name, String status) {
        Student student = new Student(id, name, status);
        studentRepository.save(student);
    }

    public StudentDTO getInfo(String name) {
        Student student = studentRepository.findByName(name);
        StudentDTO studentDTO = new StudentDTO(student.getId(), student.getName(), student.getStatus());
        return studentDTO;
    }
}
