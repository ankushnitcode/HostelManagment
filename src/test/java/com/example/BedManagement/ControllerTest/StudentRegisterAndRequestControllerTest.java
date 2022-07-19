package com.example.BedManagement.ControllerTest;

import com.example.BedManagement.Controllers.StudentRegistrationAndRequestController;
import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.apache.logging.log4j.message.ObjectMessage;
import org.junit.Before;
import org.junit.Test;
import org.junit.platform.engine.TestExecutionResult;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Status;
import java.util.Optional;
import java.util.logging.Logger;


import static org.assertj.core.api.BDDAssumptions.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class StudentRegisterAndRequestControllerTest{
    @Mock
    StudentRepository studentRepository;
    @Mock
    StudentRegisterService studentRegisterService;
    @InjectMocks
    StudentRegistrationAndRequestController studentRegistrationAndRequestController;
    @Autowired
    private MockMvc mockMvc;
    private ObjectMapper objectMapper= new ObjectMapper();

     ObjectWriter ow = objectMapper.writer();

   @Before
   public void setUp(){
       MockitoAnnotations.initMocks(this);
       mockMvc = MockMvcBuilders.standaloneSetup(studentRegistrationAndRequestController).build();


   }
    @Test
    public void studentRegisterAndGivingResponseTest() throws Exception{

      StudentInfo student = StudentInfo.builder().name("ankush")
                      .gender("male").haveBed(false).build();

      //
        // Mockito.when(studentRepository.save(student)).thenReturn(student);
        String content = ow.writeValueAsString(student);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/HostelSystem/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                                .content(content);
       mockMvc.perform(mockRequest).andExpect(status().isCreated());

    }
}