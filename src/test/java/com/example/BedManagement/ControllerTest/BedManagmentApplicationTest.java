package com.example.BedManagement.ControllerTest;

import com.example.BedManagement.entity.Student;
import com.example.BedManagement.repository.StudentRepository;
import com.example.BedManagement.services.Impl.StudentRegisterServiceImpl;
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
       // assertEquals(2,service.getStudent().size());
    }

    @Test
    public void createNewStudent(){
        Student newStudent = new Student();
        newStudent.setStudentId(1);
        newStudent.setStudentName("Test1");
        newStudent.setStudentGender("Male");
        newStudent.setHaveBed(null);
       // newStudent.setRoom(null);
        assertEquals(new Student("Test1","Male",null),newStudent);
    }

    @Test
    public void assigningBedToStudentTest(){

    }


}