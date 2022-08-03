package com.example.BedManagement.controllers;

import com.example.BedManagement.entity.Student;
//import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.exception.HostelNotFoundException;
import com.example.BedManagement.repository.StudentRepository;
import com.example.BedManagement.services.StudentRegisterService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


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
        return new ResponseEntity<>(savedStudent,HttpStatus.CREATED);
    }


    @PostMapping("/request/{id}")
    public ResponseEntity<Object> studentBedRequest(@PathVariable int id ) throws HostelNotFoundException {
        if(!studentRepository.existsById(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);}
        else if(studentRepository.findById(id).get().getHaveBed()==true){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
            else{
                studentRegisterService.bedRequestOperation(id);
              return new   ResponseEntity<>(studentRepository.findById(id),HttpStatus.OK);
            }

        }

    }

