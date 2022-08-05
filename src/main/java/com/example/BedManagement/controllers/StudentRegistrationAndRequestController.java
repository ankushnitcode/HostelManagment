package com.example.BedManagement.controllers;

import com.example.BedManagement.entity.Student;
//import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.exception.HostelNotFoundException;
import com.example.BedManagement.repository.StudentRepository;
import com.example.BedManagement.services.StudentRegisterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/HostelSystem")
@Api(tags = "Hostel Management System Registration APIs")
public class StudentRegistrationAndRequestController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentRegisterService studentRegisterService;
    @RequestMapping("/home")
  String homePage(){
        return "Welcome";
    }
    @ApiImplicitParam(name = "student", value = "The request object that will used to create a student and register into our database.", dataTypeClass = Student.class)
    @ApiOperation(value = " To register new student", tags = {"Hostel System APIs"}, httpMethod = "POST")
    @PostMapping("/students")
    @ResponseBody
    public ResponseEntity<Object> createStudent(@Valid @RequestBody Student student) {
        Student newStudent;
        newStudent = studentRegisterService.createNewStudent(student);
        Student savedStudent = studentRepository.save(newStudent);
        return new ResponseEntity<>(savedStudent,HttpStatus.CREATED);
    }

    @ApiImplicitParam(name = "studentId", value = "The request object is student id  that will used by student to request a bed ")
    @ApiOperation(value = " To giving bed to student", tags = {"Hostel System APIs"}, httpMethod = "POST")
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

