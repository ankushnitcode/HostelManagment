package com.example.BedManagement.ControllerTest;

import com.example.BedManagement.Controllers.StudentDetailsController;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;

@RunWith(MockitoJUnitRunner.class)
@WebMvcTest(StudentDetailsController.class)
public class StudentDeatilsControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    StudentDetailsController studentDetailsController;
    @Mock
    StudentRepository studentRepository;
    @Mock
    private StudentRegisterService studentRegisterService;

    private ObjectMapper objectMapper = new ObjectMapper();

    ObjectWriter ow = objectMapper.writer();

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(studentDetailsController).build();
    }

    @Before
    public void setup() {
        studentDetailsController = new StudentDetailsController();
        mockMvc = MockMvcBuilders.standaloneSetup(studentDetailsController).build();
    }

    @Test
    public void StudentControllerTest() throws Exception {

        // when(studentRepository.existsById(anyInt())).thenReturn(true);
        //Mockito.when(studentRegisterService.findingAllStudent()).thenReturn((List<Student>) new Student(1, "tomtest", "Male", false, null));

        StudentInfo student = StudentInfo.builder().name("ankush")
                .gender("male").haveBed(false).build();

        //
        // Mockito.when(studentRepository.save(student)).thenReturn(student);
        String content = ow.writeValueAsString(student);
        //  when(studentFetailsController.retrieveStudent(2))
        //      .thenReturn(new Student(1,"tom", "Male", false,null), HttpStatus.OK);
        // .thenReturn( new ResponseEntity<>(HttpStatus.OK));

        RequestBuilder Request = MockMvcRequestBuilders.get("/HostelSystem/students/{id}", 1)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON);
        // MvcResult mvcResult =
        mockMvc.perform(Request)
                //.andExpect(status().isOk())
                //.andExpect((ResultMatcher) content().json("{\"studentId\":1,\"studentName\":\"tom\",\"studentGender\":\"Male\",\"haveBed\":false,\"room\":null})"))
                .andExpect((ResultMatcher) content().json("[{studentId:1,studentName:tom,studentGender:Male,haveBed:false,roomId:null}]"))
                .andReturn();

        //Verify
        //assertEquals( mvcResult,mvcResult.getResponse());
    }


}
