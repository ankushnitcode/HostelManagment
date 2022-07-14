package com.example.BedManagement.Services.Impl;

import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class StudentRegisterServiceImpl implements StudentRegisterService {
    @Autowired
    StudentRepository studentRepository;


    @Override
    public List<Student> findingAllStudent() {
        List<Student> studentInfoList = studentRepository.findAll();
        return studentInfoList;
    }

    public Student createNewStudent(StudentInfo studentInfo) {
        Student newStudent = new Student();
        newStudent.setStudentName(studentInfo.getName());
        newStudent.setStudentGender(studentInfo.getGender());
        newStudent.setHaveBed(studentInfo.getHaveBed());
        return newStudent;
    }

    public StudentInfo studentResponse(Optional<Student> student) {
        Student newStudent = new Student();
        newStudent = student.get();
        StudentInfo studentResponse = new StudentInfo();
        studentResponse.setName(newStudent.getStudentName());
        studentResponse.setGender(newStudent.getStudentGender());
        studentResponse.setHaveBed(newStudent.getHaveBed());
        return studentResponse;}}
