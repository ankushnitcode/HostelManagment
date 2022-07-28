package com.example.BedManagement.ControllerTest;

import com.example.BedManagement.Controllers.StudentRegistrationAndRequestController;
import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Exception.HostelNotFoundException;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
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

      Student student = Student.builder().studentName("ankush")
                      .studentGender("male").haveBed(false).build();
        String content = ow.writeValueAsString(student);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/HostelSystem/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                                .content(content);
       mockMvc.perform(mockRequest).andExpect(status().isCreated());

    }
//    @Test
//    public void studentBedRequestTest() throws Exception, HostelNotFoundException {
//        Student student = Student.builder().studentId(1).studentName("ankush")
//                .studentGender("male").haveBed(false).build();
//        String content = ow.writeValueAsString(student);
//
//        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
//                .post("/HostelSystem/request/1").
//                contentType(MediaType).
//                accept(MediaType.ALL).content(1);
//
//        mockMvc.perform(mockRequest).andExpect(status().isOk());
//
//
//    }
}