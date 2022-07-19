package com.example.BedManagement.ControllerTest;

import com.example.BedManagement.Controllers.StudentRegistrationAndRequestController;
import com.example.BedManagement.Model.StudentInfo;
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
    public void studentRegisterResponseTest() throws Exception{

      StudentInfo student = StudentInfo.builder().name("ankush")
                      .gender("male").haveBed(false).build();

      //
        // Mockito.when(studentRepository.save(student)).thenReturn(student);
        String content = ow.writeValueAsString(student);

        MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
                .post("/students")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                                .content(content);
       mockMvc.perform(mockRequest).andExpect(status().isCreated());

    }
//    @Test
//    public void bedRequest() throws Exception{
//        when(studentRegistrationAndRequestController.bedRequest(1))
//                .thenReturn(HttpStatus.OK);
//
//        RequestBuilder Request = MockMvcRequestBuilders.get("/HostelSystem/request/{id}",1)
//                .accept(MediaType.APPLICATION_JSON);
//        MvcResult mvcResult = mockMvc.perform(Request)
//                .andExpect(status().isOk())
//                //.andExpect((ResultMatcher) content().json("{studentId:1,studentName:tom,studentGender:Male,haveBed:false,room:null}"))
//                .andReturn();
//    }
}