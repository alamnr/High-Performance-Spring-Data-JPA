package com.example.highperformancespringdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.example.highperformancespringdatajpa.connections.SampleController;

@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class RestApplicationTest {
    
    @LocalServerPort
    private int port;
    @Autowired
    private SampleController sampleController;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void contextLoads(){
        assertThat(sampleController).isNotNull();
    }

    @Test
    void greetingShouldReturnDefaultMessage(){
            assertThat(this.restTemplate.getForObject("http://localhost:"+port+"/hello", String.class)).contains("Hello, World");
    }

}
