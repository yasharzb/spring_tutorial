package com.example.test.security;

import com.example.test.configuration.ControllerConfig;
import com.example.test.model.entities.Student;
import com.example.test.model.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

@RunWith(SpringRunner.class)
@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@TestPropertySource("classpath:application-test.properties")
public class BasicAuthenticationProviderTests {
    @Autowired
    private StudentRepository authenticationInfoRepository;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Autowired
    private ControllerConfig controllerConfig;

    @Before
    public void setUp() {
        authenticationInfoRepository.deleteAll();
    }

    @Test
    public void authenticationFailedTest() {
        Student student = new Student(1, "yashar", "123", "yashar", "hi");
        studentRepository.save(student);
        String username = "yashar";
        String password = "123";
        StringBuilder uri = new StringBuilder();
        uri.append(controllerConfig.getUri()).append("/get_info");
        ResponseEntity<String> responseEntity = testRestTemplate.withBasicAuth(username, password).getForEntity(uri.toString(), String.class);
        assertNotEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
    }
}
