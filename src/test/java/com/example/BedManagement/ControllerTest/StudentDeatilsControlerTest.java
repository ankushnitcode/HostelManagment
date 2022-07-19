package com.example.BedManagement.ControllerTest;

import com.example.BedManagement.Controllers.StudentDetailsController;
import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(StudentDetailsController.class)
public class StudentDeatilsControlerTest {
   @Mock
   StudentRepository studentRepository;
    @MockBean
    StudentRegisterService studentRegisterService;
   @InjectMocks
   StudentDetailsController studentDetailsController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void StudentControllerTest() throws Exception {

        when(studentDetailsController.retrieveStudent(1))
                .thenReturn((ResponseEntity<StudentInfo>) Arrays.asList(new Student(1, "tom", "Male", false, null)));

        RequestBuilder Request = MockMvcRequestBuilders.get("/HostelSystem/students/{id}",1)
                .accept(MediaType.APPLICATION_JSON);
        MvcResult mvcResult = mockMvc.perform(Request)
                .andExpect(status().isOk())
                //.andExpect((ResultMatcher) content().json("{\"studentId\":1,\"studentName\":\"tom\",\"studentGender\":\"Male\",\"haveBed\":false,\"room\":null})"))
                .andExpect((ResultMatcher) content().json("[{studentId:1,studentName:tom,studentGender:Male,haveBed:false,room:null}]"))
                .andReturn();

        //Verify
        //assertEquals( mvcResult,mvcResult.getResponse());
    }

}
