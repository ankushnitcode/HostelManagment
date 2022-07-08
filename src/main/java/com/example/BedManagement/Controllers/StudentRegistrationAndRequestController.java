package com.example.BedManagement.Controllers;

import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Services.StudentRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentRegistrationAndRequestController {
    @Autowired
    StudentRegisterService studentRegisterService;
    @RequestMapping("/")
  String welcomePage(){
        return "index";
    }
    ResponseEntity<Student>registerStudent(Student student){
        Student newStudent = this.studentRegisterService.registerStudent(student);
        return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
    }
}
