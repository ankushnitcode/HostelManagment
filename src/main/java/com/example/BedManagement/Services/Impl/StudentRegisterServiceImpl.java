package com.example.BedManagement.Services.Impl;

import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Repository.StudentRepository;
import org.springframework.stereotype.Service;

@Service
public class StudentRegisterServiceImpl implements StudentRegisterService {
    StudentRepository studentRepository;

    @Override
    public void registerStudent(StudentInfo student){
        if(student.getHaveBed()==false)
        studentRepository.save(student);
    }

}
