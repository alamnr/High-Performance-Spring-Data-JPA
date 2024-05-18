package com.example.highperformancespringdatajpa.connection;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.BDDMockito.given;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.highperformancespringdatajpa.connections.ExternalService;
import com.example.highperformancespringdatajpa.connections.Person;
import com.example.highperformancespringdatajpa.connections.SampleService;
import com.example.highperformancespringdatajpa.dto.PersonDto;
import com.fasterxml.jackson.databind.ObjectMapper;

//@WebMvcTest(controllers = SampleController.class)
 @SpringBootTest
 @AutoConfigureMockMvc(addFilters=false)
public class SampleControllerMockMvcTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SampleService sampleService;
    @MockBean
    private ExternalService externalService;

    @Autowired
    private ObjectMapper objectMapper;

    private Person person;
    private PersonDto personDto;

    @BeforeEach
    void init(){
        person = Person.builder().id("id-1").name("John").build();
        personDto = new PersonDto("id-1", "John");

    }

    @Test
    public void sampleController_createPerson_returnCreated() throws Exception{
        given(sampleService.createPerson(ArgumentMatchers.any()))
                    .willAnswer(invocation -> invocation.getArgument(0));
        ResultActions response = mockMvc.perform(post("/person/create")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(objectMapper.writeValueAsString(personDto)));
        
        

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id", CoreMatchers.is(personDto.id())))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(personDto.name())));
                                        
    }

}


