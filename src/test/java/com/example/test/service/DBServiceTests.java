package com.example.test.service;

import com.example.test.model.entities.Student;
import com.example.test.model.exception.ExistingStudentException;
import com.example.test.model.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@Slf4j
public class DBServiceTests {
    @Autowired
    private DBService dbService;
    @Autowired
    private StudentRepository studentRepository;

    //****
    @Mock
    private StudentRepository mockedStudentRepository;

    @InjectMocks
    private DBService injectedDBService;

    private static final int ID = 3;
    private static final String NAME = "$name";
    private static final String STATUS = "$status";

    @Before
    public void setUp() {
        studentRepository.deleteAll();
    }

    @Test
    public void registerSuccessfulTest() {
        dbService.register(ID, NAME, STATUS);
        Student student = new Student();
        studentRepository.findById(ID).ifPresent(st -> {
            student.setId(st.getId());
            student.setName(st.getName());
            student.setStatus(st.getStatus());
        });
        assertEquals(ID, student.getId());
        assertEquals(NAME, student.getName());
        assertEquals(STATUS, student.getStatus());
    }

    @Test(expected = ExistingStudentException.class)
    public void registerUnsuccessfulTest() {
        Student saved = new Student(NAME, STATUS);
        saved.setId(ID);
        studentRepository.save(saved);
        dbService.register(ID, NAME + NAME, STATUS);
        Student student = new Student();
        studentRepository.findById(ID).ifPresent(st -> {
            student.setId(st.getId());
            student.setName(st.getName());
            student.setStatus(st.getStatus());
        });
        assertEquals(NAME, student.getName());
    }

    @Test
    public void mockedRegisterSuccessfulTest() {
/*
        Student student = mock(Student.class);
        //Studnet -> getId -> ID
        when(student.getId()).thenReturn(ID);
        doNothing().when(student).setName(NAME);
        when(student.getStatus()).thenCallRealMethod();
        doCallRealMethod().when(student).setStatus(STATUS);
        log.info("Done");
*/
        AtomicBoolean isSaved = new AtomicBoolean(false);
        Optional<Student> mockedAnswer = Optional.empty();
        when(mockedStudentRepository.findById(ID)).thenReturn(mockedAnswer);
        when(mockedStudentRepository.save(any(Student.class))).thenAnswer(invocation -> {
            isSaved.set(true);
            return null;
        });
        injectedDBService.register(ID, NAME, STATUS);
        assertTrue(isSaved.get());
    }

    @Test(expected = ExistingStudentException.class)
    public void mockedRegisterUnsuccessfulTest() {
        AtomicBoolean isSaved = new AtomicBoolean(false);
        Optional<Student> mockedAnswer = Optional.of(new Student());
        when(mockedStudentRepository.findById(ID)).thenReturn(mockedAnswer);
        when(mockedStudentRepository.save(any(Student.class))).thenAnswer(invocation -> {
            isSaved.set(true);
            return null;
        });
        injectedDBService.register(ID, NAME, STATUS);
        assertFalse(isSaved.get());
    }

    @Test
    public void getInfo() {

    }
}
