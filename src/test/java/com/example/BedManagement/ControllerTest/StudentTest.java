package com.example.BedManagement.ControllerTest;

import com.example.BedManagement.Controllers.StudentDetailsController;
import com.example.BedManagement.Entity.Student;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentDetailsController.class)
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    StudentRegisterServiceImpl studentRegisterService;
    @MockBean
    HostelRepository hostelRepo;
    @MockBean
    StudentRepository studentRepo;

    @Test
    public void SDCTest() throws Exception {
        Student student = new Student();
        student.setStudentName("TestKaTest");
        student.setStudentGender("Male");
        student.setHaveBed(null);
        String inputInJSON = this.mapToJson(student);
        String URI = "/HostelSystem/students/1";

        Mockito.when(studentRegisterService.createNewStudent(Mockito.any(Student.class))).thenReturn(student);
       // System.out.println(student + "@@@@@@@@@@@@@");
        RequestBuilder request = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
        MvcResult result = mockMvc.perform(request).andReturn();
       // String expected = student//this.mapToJson(student);//String.valueOf(student);
        String actual = result.getResponse().getContentAsString();
        //System.out.println(actual + "#############");
        System.out.println(student + "@@@@@@@@@@@@@");
        System.out.println(result.getResponse().getContentAsString() + "#############");
        assertThat(student).isEqualTo(result.getResponse());
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
//        String content = mvcResult.getResponse().getContentAsString();
//        System.out.println(content + "@@@@@@@@@@");
//        assertEquals(content, "Student is created successfully");
    }

//    @Test
//    public void getStudentList() throws Exception {
//        String uri = "/HostelSystem/students";
//        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.get(uri)
//                .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
//
//        int status = mvcResult.getResponse().getStatus();
//        assertEquals(200, status);
//        String content = mvcResult.getResponse().getContentAsString();
//        List<Student> productlist = this.mapFromJson(content, List<Student>.class);
//        assertTrue(studentlist.length > 0);
//    }
//
//    private List<Student> mapFromJson(String content, Class<List> listClass) {
//        return List<content>;
//    }


    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

//    @Test
//   // @Order(5)
//    public void deleteStudent(){
//        studentRepository.deleteById(26);
//        assertThat(studentRepository.existsById(26)).isFalse();
//    }
}
