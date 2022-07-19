package com.example.BedManagement.Controllers;

import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentNotFoundException;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@Getter
@Setter
@RequestMapping("HostelSystem")
public class StudentDetailsController {

    @Autowired
    private StudentRegisterService studentRegisterService;
    @Autowired
    private StudentRepository studentRepository;

    // retrieve all students - GET/students
    @GetMapping("/students")
    public List<Student> retrieveAllStudent() {

        return studentRegisterService.findingAllStudent();
    }



    // Retrieve student(Integer id) - GET/students/{id}
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> retrieveStudent(@PathVariable int id) {

     if(studentRepository.existsById(id)){
         Student student = studentRepository.findById(id).get();
         return new ResponseEntity<>(student, HttpStatus.OK);
     }
     else{
         throw new StudentNotFoundException("Id-" + id);
     }
    }

    @DeleteMapping("/students/{id}")
    public Student deleteStudent(@PathVariable("id") int id) {
       Optional<Student> student = studentRepository.findById(id);
        if(student.isPresent()){
            studentRepository.delete(student.get());
        }
        else
        {
            throw new StudentNotFoundException("Id-" + id);
        }

        return student.get();
    }

    @PutMapping("/students")
    public Student updateStudent(@RequestBody Student studentObj){
        studentRepository.save(studentObj);
        return studentObj;
    }
    }
