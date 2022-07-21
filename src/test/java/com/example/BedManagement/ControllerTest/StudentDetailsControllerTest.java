package com.example.BedManagement.ControllerTest;

import com.example.BedManagement.Controllers.StudentDetailsController;
import com.example.BedManagement.Controllers.StudentRegistrationAndRequestController;
import com.example.BedManagement.Entity.Student;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class StudentDetailsControllerTest {

    @Mock
    StudentRepository studentRepository;
    @Mock
    StudentRegisterService studentRegisterService;

    @Autowired
    private MockMvc mockMvc;

    @InjectMocks
    StudentDetailsController studentDetailsController;
    private ObjectMapper objectMapper= new ObjectMapper();

    ObjectWriter ow = objectMapper.writer();
    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentDetailsController).build();


    }
    @Test
    public void StudentControllerTest() throws Exception {

    //    when(studentRepository.existsById(anyInt())).thenReturn(true);


        when(studentDetailsController.retrieveStudent(1))
                .thenReturn( new ResponseEntity<Student>(new Student("tom", "Male", false), HttpStatus.OK));
        // .thenReturn( new ResponseEntity<>(HttpStatus.OK));
        RequestBuilder Request = MockMvcRequestBuilders.get("/HostelSystem/students/{id}",1)
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(Request)
                .andExpect(status().isOk())
                //.andExpect((ResultMatcher) content().json("{\"studentId\":1,\"studentName\":\"tom\",\"studentGender\":\"Male\",\"haveBed\":false,\"room\":null})"))
                .andExpect((ResultMatcher) content().json("[{studentId:1,studentName:tom,studentGender:Male,haveBed:false,roomId:null}]"))
                .andReturn();

        //Verify
        //assertEquals( mvcResult,mvcResult.getResponse());

    }
}
