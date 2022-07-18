package com.example.BedManagement.Services;

import com.example.BedManagement.Entity.Room;
import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Model.StudentNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

public interface StudentRegisterService {


     List<Student> findingAllStudent();
     Student createNewStudent(StudentInfo studentInfo);
     Student createStudentResponse(Student student);
     Room assigningBedToStudent(int id,List<Room>roomList);
     void assigningRoomToHostel(int id);
}
