package com.example.test.model.entities;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "student_table")
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
@Data
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    @Column(name = "name_col")
    @NonNull
    private String name;
    @NonNull
    private String status;
}
