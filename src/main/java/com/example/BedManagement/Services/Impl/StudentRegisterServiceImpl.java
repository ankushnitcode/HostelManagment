package com.example.BedManagement.Services.Impl;

import com.example.BedManagement.Entity.Hostel;
import com.example.BedManagement.Entity.Room;
import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;
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
