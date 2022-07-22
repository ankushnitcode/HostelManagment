package com.example.BedManagement.Controllers;

import com.example.BedManagement.Entity.Student;
//import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Exception.HostelNotFoundException;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
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
    public ResponseEntity<Object> createStudent(@Valid @RequestBody Student student) {
        Student newStudent;
        newStudent = studentRegisterService.createNewStudent(student);
        Student savedStudent = studentRepository.save(newStudent);
       // URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedStudent.getStudentId()).toUri();
       // ResponseEntity.created(location).build();
        return new ResponseEntity<>(savedStudent,HttpStatus.CREATED);
    }
    @PostMapping("/request/{id}")
    public ResponseEntity<Object> studentBedRequest(@PathVariable int id ) throws HostelNotFoundException {
        if(!studentRepository.existsById(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
            else{
                studentRegisterService.assigningRoomToHostel(id);
              return new   ResponseEntity<>(HttpStatus.OK);
            }

        }

    }

