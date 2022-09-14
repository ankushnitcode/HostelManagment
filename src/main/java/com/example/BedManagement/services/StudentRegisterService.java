package com.example.BedManagement.services;

import com.example.BedManagement.entity.BoysRoom;
import com.example.BedManagement.entity.GirlsRoom;
import com.example.BedManagement.entity.Student;
import com.example.BedManagement.exception.HostelNotFoundException;


import java.util.List;

public interface StudentRegisterService {


     List<Student> findingAllStudent();
    Student createNewStudent(Student student);
     Student createStudentResponse(Student student);
     List<BoysRoom> assigningBedToBoyStudent(int id, List<BoysRoom> boysRoomList);
    List<GirlsRoom> assigningBedToGirlsStudent(int id, List<GirlsRoom> girlsRoomList);
     void bedRequestOperation(int id) throws HostelNotFoundException;
}
