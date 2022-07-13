package com.example.BedManagement.Controllers;

import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;


@RestController
@RequestMapping("/HostelSystem")
public class StudentRegistrationAndRequestController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentRegisterService studentRegisterService;
    @RequestMapping("/home")
  String homePage(){
        return "Welcome";
    }

    @PostMapping("/students")
    @ResponseBody
    public StudentInfo createStudent(@RequestBody StudentInfo studentInfoBody)
    {
        Student student =
                new Student();
        student = studentRegisterService.createNewStudent(studentInfoBody);
        Student savedStudent = studentRepository.save(student);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent.getStudentId()).toUri();
        ResponseEntity.created(location).build();
        return studentInfoBody;
    }
               //}


//    @PostMapping("/students")
//    public ResponseEntity<Object> createStudent(@RequestBody StudentInfo studentInfoBody) {
//        Student student = new Student();
//        student= studentRegisterService.createNewStudent(studentInfoBody);
//        Student savedUser = studentRepository.save(student);
//        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getStudentId())
//                .toUri();
//        return ResponseEntity.created(location).build();
//    }
}
