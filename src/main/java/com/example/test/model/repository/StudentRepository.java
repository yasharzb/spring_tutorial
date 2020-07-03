package com.example.test.model.repository;

import com.example.test.model.entities.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {
    Student findByName(String name);

    boolean existsByUsernameAndPassword(String username, String password);
}
