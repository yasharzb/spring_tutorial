package com.example.test.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@TestPropertySource(locations = "classpath:application-test.properties")
@SpringBootTest
@Slf4j
public class ClientMachineTest {
    @Autowired
    private ClientMachine clientMachine;

    @Test
    public void registerTest(){
        String result = clientMachine.callApi();
        log.info(result);
    }
}
