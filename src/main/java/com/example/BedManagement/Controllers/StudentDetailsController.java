package com.example.BedManagement.Controllers;

import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Model.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("HostelSystem")
public class StudentDetailsController {

    @Autowired
    private Student service;

    // retrieve all users - GET/users
    @GetMapping("/students")
    public List<StudentInfo> retrieveAllUsers() {

        return service.findAll();
    }

    // Retrieve user(Integer id) - GET/students/{id}
    @GetMapping("/students/{id}")
    public StudentInfo retrieveUser(@PathVariable int id) {

        StudentInfo student = service.findOne(id);

        if (student == null) {
            throw new UserNotFoundException("Id-" + id);
        }

        return student;
    }

    @PostMapping("/students")
    public ResponseEntity<Object> createUser(@RequestBody StudentInfo student) {
        StudentInfo savedUser = service.save(student);

        // CREATED
        // /users/{id} savedUser.getId()
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
