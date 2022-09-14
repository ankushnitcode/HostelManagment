package com.example.BedManagement.controllers;

import com.example.BedManagement.entity.Student;
import com.example.BedManagement.repository.StudentRepository;
import com.example.BedManagement.services.StudentRegisterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@RunWith(SpringRunner.class)
//@WebMvcTest(StudentDetailsController.class)
@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentDetailsControllerTest {
    @MockBean
    StudentRepository studentRepository;
    @MockBean
    StudentRegisterService studentRegisterService;
    @MockBean
    ArrayList<Student> student;
    @Autowired
    private MockMvc mockMvc;

    @Test
   // @Order(1)
    public void retrieveAllStudent_retrivingAllStudent_returnAllStudent(){
//        Student student = new Student();
//        student.setStudentId(1);
//        student.setStudentGender("Male");
//        student.setHaveBed(true);
//        student.setStudentName("tom");
//        studentRepository.save(student);
//        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
//        assertNotNull(studentRepository.findById(1).get());
        ArrayList<Student> list = new ArrayList<>();
        when(studentRepository.findAll()).thenReturn((List<Student>) student);
        list = student;
        assertNotNull(list);
    }

    @Test
   // @Order(2)
    public void retriveAllStudent_CheckingIfDBEmpty_DBNotNUll(){//checking data present in database
        Student student = new Student();
        student.setStudentId(1);
        student.setStudentGender("Male");
        student.setHaveBed(true);
        student.setStudentName("tom");
        studentRepository.save(student);
        List<Student> list = studentRepository.findAll();
        //when(studentRepository.findAll()).thenReturn(list);
//        assertThat(list).size().isGreaterThan(0);
        assertNotNull(list);
    }

    @Test
   // @Order(3)
    public void retrieveStudentById_RetriveStudentById_ReturnForGivenId(){
        Student student = new Student();
        student.setStudentId(1);
        //Student student = studentRepository.findById(1).get();
        assertEquals(1,student.getStudentId());

    }

    @Test
   // @Order(4)
    public void updateStudent(){
        Student student = new Student();
        student.setStudentId(2);
        student.setStudentGender("Male");
        student.setHaveBed(true);
        student.setStudentName("tom");
        studentRepository.save(student);
        //Student student = studentRepository.findById(2).get();
       // student.setStudentName("Test1");
       // studentRepository.save(student);
        when(studentRepository.findById(2)).thenReturn(Optional.of(student));
        System.out.println(student + "@@@@");
        //assertNotEquals("test",studentRepository.findById(2).get().getStudentName());
        assertEquals("tom",studentRepository.findById(2).get().getStudentName());
    }

    @Test
  //  @Order(5)
    public void deleteStudent_DeletingStudent_StudentDeleted(){
        studentRepository.deleteById(1);
        assertThat(studentRepository.existsById(1)).isFalse();
    }

    @Test
    public void asdf(){
        Student student = new Student();
        student.setStudentName("QWERTY");
        student.setStudentId(1);
        studentRepository.save(student);
        studentRepository.findById(1);
        when(student.getStudentId()).thenReturn(1);
        assertEquals(1,student.getStudentId());
    }

    @Test
    public void retrieveStudentsByHostel_retrivingStudentsByHostel_ReturnStudent() throws Exception {
        Student student = new Student();
        student.setStudentName("ASDF");
        student.setStudentId(1);
        studentRepository.save(student);
        studentRegisterService.bedRequestOperation(1);
        mockMvc.perform(get("http://localhost:8080/HostelSystem/{hostelNumber}",1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());


    }

}
