package com.example.BedManagement.Services.Impl;

import com.example.BedManagement.Entity.Room;
import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Repository.RoomRepository;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentRegisterServiceImpl implements StudentRegisterService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    RoomRepository roomRepository;



    @Override
    public List<Student> findingAllStudent() {
        List<Student> studentInfoList = studentRepository.findAll();
        return studentInfoList;
    }
    public Student createNewStudent(StudentInfo studentInfo){
        Student newStudent = new Student();
        newStudent.setStudentName(studentInfo.getName());
        newStudent.setStudentGender(studentInfo.getGender());
        newStudent.setHaveBed(studentInfo.getHaveBed());
        return newStudent;
    }

    public StudentInfo createStudentResponse(Optional<Student> student){
        Student student1= new Student();
        student1 = student.get();
        StudentInfo studentResponse = new StudentInfo();
        studentResponse.setName(student1.getStudentName());
        studentResponse.setGender(student1.getStudentGender());
        studentResponse.setHaveBed(student1.getHaveBed());
        return studentResponse;
    }
    public void assigningBedToStudent(int id){
        Student assigningStudent = studentRepository.findById(id).get();
        Room room = new Room();
        List<Student> bedList = new ArrayList<>();
        assigningStudent.setHaveBed(true);
        assigningStudent.setRoom(room);
        bedList.add(assigningStudent);
        //room.setStudentList(bedList);
        studentRepository.save(assigningStudent);
        roomRepository.save(room);

    }

    public List<Student> getStudent() {
        List<Student> students = studentRepository.findAll();
        //System.out.println("Getting data from DB: " + students);// for testing purpose
        return students;
    }

//    public Student deleteStudent(int studentId){
//
//        if(studentRepository.existsById(studentId)){
//            studentRepository.
//        }
//
//    }

}
