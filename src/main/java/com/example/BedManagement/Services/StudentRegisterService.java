package com.example.BedManagement.Services;

import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentInfo;

import java.util.List;
import java.util.Optional;

public interface StudentRegisterService {


     List<Student> findingAllStudent();
     Student createNewStudent(StudentInfo studentInfo);
     StudentInfo studentResponse(Optional<Student> student);
}
