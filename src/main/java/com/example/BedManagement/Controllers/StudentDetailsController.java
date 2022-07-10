package com.example.BedManagement.Controllers;

import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Model.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("HS")
public class StudentDetailsController {

    @Autowired
    private Student service;

//    public StudentDetailsController(Student service) {
//        this.service = service;
//    }

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

}
