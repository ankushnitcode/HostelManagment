package com.example.BedManagement.ControllerTest;

import com.example.BedManagement.Controllers.StudentDetailsController;
import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Repository.BoysRoomRepository;
import com.example.BedManagement.Repository.GirlsRoomRepository;
import com.example.BedManagement.Repository.HostelRepository;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.Impl.StudentRegisterServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentDetailsController.class)

public class StudentTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    StudentRegisterServiceImpl studentRegisterService;
    @MockBean
    HostelRepository hostelRepo;
    @MockBean
    StudentRepository studentRepo;
    @MockBean
    BoysRoomRepository boysRoomRepository;
    @MockBean
    GirlsRoomRepository girlsRoomRepository;

    @Test
    public void SDCTest() throws Exception {
        Student student = new Student();
        student.setStudentName("TestKaTest");
        student.setStudentGender("Male");
        student.setHaveBed(null);
        student.setStudentId(1);
        String inputInJSON = this.mapToJson(student);
        String URI = "/HostelSystem/students";

        Mockito.when(studentRegisterService.createNewStudent(Mockito.any(Student.class))).thenReturn(student);
        RequestBuilder request = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andReturn();
        String actual = result.getResponse().getContentAsString();
        int status = result.getResponse().getStatus();
        assertEquals(200,status);
        System.out.println(result.getResponse().getContentAsString() + "#############");
    }

    @Test
    public void createNewStudent() throws Exception {
        String uri = "/HostelSystem/students";
        Student student = new Student();
        student.setStudentId(1);
        student.setStudentName("Ginger");

        String inputJson = this.mapToJson(student);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        System.out.println(status + "@@@@@@@");
    }



    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
