package com.example.BedManagement.controllers;

import com.example.BedManagement.entity.Student;
import com.example.BedManagement.repository.StudentRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
//@WebMvcTest(StudentDetailsController.class)
@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentDetailsControllerTest {
    @MockBean
    StudentRepository studentRepository;
    @MockBean
    Student student;

    @Before
    public void init() {
        Student newStudent = new Student();
        student.setStudentId(1);
        student.setStudentGender("Male");
        student.setHaveBed(true);
        student.setStudentName("tom");
        studentRepository.save(student);
    }

    @Test
   // @Order(1)
    public void retrieveAllStudent_retrivingStudentFromDB_returnAllStudent(){
//        Student student = new Student();
//        student.setStudentId(1);
//        student.setStudentGender("Male");
//        student.setHaveBed(true);
//        student.setStudentName("tom");
//        studentRepository.save(student);
        when(studentRepository.findById(1)).thenReturn(Optional.of(student));
        assertNotNull(studentRepository.findById(1).get());
    }

    @Test
   // @Order(2)
    public void retriveAllStudent_CheckingIfDBEmpty_DBNotNUll(){//checking data present in database
//        Student student = new Student();
//        student.setStudentId(1);
//        student.setStudentGender("Male");
//        student.setHaveBed(true);
//        student.setStudentName("tom");
//        studentRepository.save(student);
        List<Student> list = studentRepository.findAll();
        when(studentRepository.findAll()).thenReturn(list);
        assertThat(list).size().isGreaterThan(0);
    }

    @Test
   // @Order(3)
    public void retrieveStudentById_RetriveStudentById_ReturnForGivenId(){
        Student student = studentRepository.findById(1).get();
        assertEquals(1,student.getStudentId());

    }

    @Test
   // @Order(4)
    public void updateStudent(){

        Student student = studentRepository.findById(2).get();
        student.setStudentName("Test1");
        studentRepository.save(student);
        when(studentRepository.findById(2)).thenReturn(Optional.of(student));
        System.out.println(student + "@@@@");
        assertNotEquals("Test",studentRepository.findById(2).get().getStudentName());
    }

    @Test
  //  @Order(5)
    public void deleteStudent(){
        studentRepository.deleteById(1);
        assertThat(studentRepository.existsById(1)).isFalse();
    }

}
