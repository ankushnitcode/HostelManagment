package com.example.BedManagement.controllers;

import com.example.BedManagement.entity.GirlsRoom;
import com.example.BedManagement.entity.Hostel;
import com.example.BedManagement.entity.BoysRoom;
import com.example.BedManagement.entity.Student;
import com.example.BedManagement.exception.StudentNotFoundException;
import com.example.BedManagement.repository.BoysRoomRepository;
import com.example.BedManagement.repository.GirlsRoomRepository;
import com.example.BedManagement.repository.HostelRepository;
import com.example.BedManagement.repository.StudentRepository;
import com.example.BedManagement.services.StudentRegisterService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("HostelSystem")
@Api(tags = "Hostel Management System APIs")
public class StudentDetailsController {

    @Autowired
    BoysRoomRepository boysRoomRepository;
    @Autowired
    GirlsRoomRepository girlsRoomRepository;
    @Autowired
    private StudentRegisterService studentRegisterService;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private HostelRepository hostelRepository;

    @ApiOperation(value = " To retrieve all the students", tags = {"Hostel System APIs"}, httpMethod = "GET")
    @GetMapping("/students")
    public List<Student> retrieveAllStudent() {

        return studentRegisterService.findingAllStudent();
    }


    @ApiImplicitParam(name = "StudentId", value = "The request object that will used to retrieve a particular student from student table.")
    @ApiOperation(value = " To retrieve particular student", tags = {"Hostel System APIs"}, httpMethod = "GET")
    @GetMapping("/students/{id}")
    public ResponseEntity<Student> retrieveStudentById(@PathVariable int id) {

        if (studentRepository.existsById(id)) {
            Student student = studentRepository.findById(id).get();
            return new ResponseEntity<>(studentRegisterService.createStudentResponse(student), HttpStatus.OK);
        } else {
            throw new StudentNotFoundException("Id-" + id);
        }

    }
    @ApiImplicitParam(name = "Hostel_No", value = "The request object that will used to find all the students in that hostel.")
    @ApiOperation(value = " To retrieve all the student from hostel", tags = {"Hostel System APIs"}, httpMethod = "GET")
    @GetMapping("/{hostelNo}")
    public ResponseEntity<List<Student>> retrieveStudentsByHostel(@PathVariable int hostelNo) {
        if (!hostelRepository.existsById(hostelNo)) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Hostel hostel = hostelRepository.findById(hostelNo).get();
        if (Objects.equals(hostel.getHostelCategory(), "Male")) {
            List<BoysRoom> boysRoomList = new ArrayList<>();
            boysRoomList = hostel.getBoysRoomList();
            List<Student> studentList = new ArrayList<>();
            for (BoysRoom boysRoom : boysRoomList) {
                boysRoom.getStudentList().forEach(student ->
                        studentList.add(studentRepository.findById(student.getStudentId()).get()));
            }
            return new ResponseEntity<>(studentList, HttpStatus.OK);

        } else {
            List<GirlsRoom> girlsRoomList = new ArrayList<>();
            girlsRoomList = hostel.getGirlsRoomList();
            List<Student> studentList = new ArrayList<>();
            for (GirlsRoom girlsRoom : girlsRoomList) {
                girlsRoom.getStudentList().forEach(student ->
                        studentList.add(studentRepository.findById(student.getStudentId()).get()));

            }
            return new ResponseEntity<>(studentList, HttpStatus.OK);
        }
    }
    @ApiImplicitParam(name = "hostelNo , room_id", value = "The request object that will used to retrieve the students from room.")
    @ApiOperation(value = " To retrieve all the student from room ", tags = {"Hostel System APIs"}, httpMethod = "GET")
    @GetMapping("/{hostelNo}/{roomId}")
    public ResponseEntity<List<Student>> retrieveStudentByRoom
            (@PathVariable(name = "hostelNo") int hostelNo, @PathVariable(name = "roomId") int roomId) {
        List<Student> studentList = new ArrayList<>();
        Hostel hostel = hostelRepository.findById(hostelNo).get();
        if (Objects.equals(hostel.getHostelCategory(), "Male")) {
            studentList = boysRoomRepository.findById(roomId).get().getStudentList();
        } else {
            studentList = girlsRoomRepository.findById(roomId).get().getStudentList();
        }
        return new ResponseEntity<>(studentList, HttpStatus.OK);
    }

}


