package com.example.BedManagement.Controllers;

import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Services.Impl.StudentRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentRegistrationAndRequestController {
    @Autowired
    StudentRegisterService studentRegisterService;
    @RequestMapping("/home")
  String welcomePage(){
        return "index";
    }
    @PostMapping("/v1")
    ResponseEntity<String>registerStudent(StudentInfo student){
        studentRegisterService.registerStudent(student);
        return new ResponseEntity<>( HttpStatus.CREATED);
    }
}
