package com.example.BedManagement.ControllerTest;

import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.Impl.StudentRegisterServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BedManagmentApplicationTest{

    private Student student;
    @Autowired
    private StudentRegisterServiceImpl service;

    @MockBean
    StudentRepository repository;

    @Test
    public void findAllStudentTest(){
        when(repository.findAll()).thenReturn(Stream.of
                        (new Student("Test1","Male",null),
                new Student("Test2","Female",null))
                .collect(Collectors.toList()));
        assertEquals(2,service.getStudent().size());
    }

    @Test
    public void createNewStudent(){
//        Student newStudent = new Student();
//        newStudent.setStudentName(studentInfo.getName());
//        newStudent.setStudentGender(studentInfo.getGender());
//        newStudent.setHaveBed(studentInfo.getHaveBed());
//        return newStudent;
        Student newStudent = new Student();
        newStudent.setStudentId(1);
        newStudent.setStudentName("Test1");
        newStudent.setStudentGender("Male");
        newStudent.setHaveBed(null);
       // newStudent.setRoom(null);
        assertEquals(new Student("Test1","Male",null,null,null,null),newStudent);
    }

    @Test
    public void assigningBedToStudentTest(){

    }

//    @Test
//    public void retriveAll(){//checking data present in database
//        List<Student> list = studentRepository.findAll();
//        // assertThat(list).size().isGreaterThan(0);
//        assertThat(list).size().isGreaterThan(0);
//    }

}