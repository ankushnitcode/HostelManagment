package com.example.BedManagement.Services.Impl;

import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;
import org.springframework.stereotype.Service;

@Service
public class StudentRegisterServiceImpl implements StudentRegisterService {
    StudentRepository studentRepository;

    @Override
    public StudentInfo registerStudent(StudentInfo student){
        if(student.getHaveBed()==false)
       StudentInfo studentInfo = this.studentRepository.save(student);


    }

}
