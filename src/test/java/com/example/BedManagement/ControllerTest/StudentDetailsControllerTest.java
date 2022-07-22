package com.example.BedManagement.ControllerTest;

import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Repository.StudentRepository;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
//@WebMvcTest(StudentDetailsController.class)
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentDetailsControllerTest {

    @Autowired
    StudentRepository studentRepository;

    @MockBean
    Student student;

    @Test
    @Order(1)
    public void retrivingStudentFromDB(){//also can create
//        Student student = new Student();
//        student.setStudentId(22);
//        student.setStudentGender("Male");
//        student.setHaveBed(true);
//        student.setStudentName("tom");
//        studentRepository.save(student);
        assertNotNull(studentRepository.findById(2).get());
    }

    @Test
    @Order(2)
    public void retriveAll(){//checking data present in database
        List<Student> list = studentRepository.findAll();
        // assertThat(list).size().isGreaterThan(0);
        assertThat(list).size().isGreaterThan(0);
    }

    @Test
    @Order(3)
    public void retriveStudentById(){
        //when(studentRepository.findById(1)).thenReturn(new Student("id"),1);
        Student student = studentRepository.findById(2).get();
        assertEquals(2,student.getStudentId());
        //assertEquals(expected-value,actual-value);

    }

    @Test
    @Order(4)
    public void updateStudent(){
        Student student = studentRepository.findById(2).get();
        student.setStudentName("Test1");
        studentRepository.save(student);
        assertNotEquals("Test",studentRepository.findById(2).get().getStudentName());
    }

    @Test
    @Order(5)
    public void deleteStudent(){
        studentRepository.deleteById(3);
        assertThat(studentRepository.existsById(3)).isFalse();
    }

}
