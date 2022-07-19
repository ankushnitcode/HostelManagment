package com.example.BedManagement.Services;

import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentInfo;

import java.util.List;

public interface StudentRegisterService {

    List<Student> findingAllStudent();
    Student createNewStudent(StudentInfo studentInfo);
    //  StudentInfo createStudentResponse(Optional<Student> student);
    void assigningBedToStudent(int id);

}