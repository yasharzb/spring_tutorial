package com.example.test.service;

import com.example.test.configuration.ControllerConfig;
import com.example.test.model.dto.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ClientMachine {
    @Autowired
    private ControllerConfig controllerConfig;

    private RestTemplate restTemplate = new RestTemplate();

    public String callApi() {
        StudentDTO studentDTO = new StudentDTO(4, "yashar", "hi");
        HttpEntity<StudentDTO> requestEntity = new HttpEntity<>(studentDTO);
        String uri = "http://127.0.0.1:8050/register";
        ResponseEntity<String> responseEntity = restTemplate.exchange(uri, HttpMethod.POST, requestEntity, String.class);
        return responseEntity.getBody();
    }
}
