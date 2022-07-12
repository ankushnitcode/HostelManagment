package com.example.BedManagement.Controllers;

import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("HostelSystem")

public class StudentDetailsController {

    @Autowired
    private StudentRegisterService studentRegisterService;
    @Autowired
    private StudentRepository studentRepository;

    // retrieve all users - GET/users
    @GetMapping("/students")
    public List<Student> retrieveAllStudent() {

        return studentRegisterService.findingAllStudent();
    }

    // Retrieve user(Integer id) - GET/students/{id}
    @GetMapping("/students/{id}")
    public ResponseEntity<StudentInfo> retrieveStudent(@PathVariable int id) {

     if(studentRepository.existsById(id)){
         Optional<Student> student = studentRepository.findById(id);
         return new ResponseEntity<>(studentRegisterService.createStudentResponse(student), HttpStatus.OK);
     }
     else{
         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
     }

    }

}
