package com.example.BedManagement.Controllers;

import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentRegistrationAndRequestController {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    StudentRegisterService studentRegisterService;

  //  @RequestMapping("/home")
 // String homePage(){
      //  return "Welcome";
  //  }
    @PostMapping("/students")
    public ResponseEntity<Object> createStudent(@RequestBody StudentInfo studentInfoBody) {
       Student student = new Student();
       student = studentRegisterService.createNewStudent(studentInfoBody);
        Student savedUser = studentRepository.save(student);
       // URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getStudentId())
               // .toUri();

        return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
    }
    @PostMapping("/request/{id}")
    public ResponseEntity<Object> bedRequest(@PathVariable int id){
        if(!studentRepository.existsById(id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        else {
            studentRegisterService.assigningBedToStudent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
