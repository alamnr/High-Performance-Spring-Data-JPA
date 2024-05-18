package com.example.highperformancespringdatajpa.connection;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.annotation.Import;

import com.example.highperformancespringdatajpa.AppTestConfiguration;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Import(AppTestConfiguration.class)
public class SampleControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void hello(){
        restTemplate.getForEntity("/hello", Void.class);
        //assertThat(restTemplate).isNotNull();
    }

    @Test
    public void external(){
        restTemplate.getForEntity("/external", Void.class);
    }

    @Test
    public void externalAfter(){
        restTemplate.getForEntity("/external-after", Void.class);
    }

    @Test
    public void nested(){
        restTemplate.getForEntity("/nested", Void.class);
    }
    
}
