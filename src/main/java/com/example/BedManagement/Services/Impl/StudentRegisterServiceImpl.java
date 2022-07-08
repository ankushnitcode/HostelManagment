package com.example.BedManagement.Services.Impl;

import com.example.BedManagement.Entity.Hostel;
import com.example.BedManagement.Entity.Room;
import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;
import org.springframework.stereotype.Service;

@Service
public class StudentRegisterServiceImpl implements StudentRegisterService {
    StudentRepository studentRepository;
    Room room;
    Hostel hostel;
    @Override
    public Student registerStudent(Student student){
        if(room.getStudentList().size()!=3&&)
        room.getStudentList().add(student);
        else hostel.getRoomList().add(room);
        Student student1= this.studentRepository.save(student);
        return student1;
    }

}
