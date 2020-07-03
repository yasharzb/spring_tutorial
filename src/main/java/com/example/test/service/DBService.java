package com.example.test.service;

import com.example.test.model.dto.StudentDTO;
import com.example.test.model.entities.Student;
import com.example.test.model.exception.ExistingStudentException;
import com.example.test.model.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DBService {
    @Autowired
    private StudentRepository studentRepository;

    public void register(int id, String name, String status) {
        Student student = new Student(name, status);
        if (studentRepository.findById(id).isEmpty()) {
            studentRepository.save(student);
            return;
        }
        throw new ExistingStudentException();
    }

    public StudentDTO getInfo(String name) {
        Student student = studentRepository.findByName(name);
        StudentDTO studentDTO = new StudentDTO(student.getId(), student.getName(), student.getStatus());
        return studentDTO;
    }
}
