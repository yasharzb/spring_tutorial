package com.example.test.model.entities;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "student_table")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Student {
    @Id
    private int id;
    @Column(name = "name_col")
    private String name;
    private String status;
}
