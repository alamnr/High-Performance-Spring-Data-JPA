package com.example.highperformancespringdatajpa;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.when;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.highperformancespringdatajpa.connections.ExternalService;
import com.example.highperformancespringdatajpa.connections.SampleController;
import com.example.highperformancespringdatajpa.connections.SampleService;


// @SpringBootTest
// @AutoConfigureMockMvc
@WebMvcTest(SampleController.class)
public class RestApplicationMockMvcTest {
    
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SampleService sampleService;
    @MockBean
    private ExternalService externalService;

    

    @Test
    public void shouldReturnDefaultMessage() throws Exception{
        assertThat(mockMvc).isNotNull();
        when(sampleService.hi()).thenReturn("Hello World");
        this.mockMvc.perform(get("/hello")).andDo(print()).andExpect(status().isOk())
                    .andExpect(content().string(containsString("Hello, World")));
        
        
    }
    
}
