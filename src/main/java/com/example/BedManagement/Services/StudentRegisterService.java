package com.example.BedManagement.Services;

import com.example.BedManagement.Entity.BoysRoom;
import com.example.BedManagement.Entity.GirlsRoom;
import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Exception.HostelNotFoundException;


import java.util.List;

public interface StudentRegisterService {


     List<Student> findingAllStudent();
    Student createNewStudent(Student student);
     Student createStudentResponse(Student student);
     List<BoysRoom> assigningBedToBoyStudent(int id, List<BoysRoom> boysRoomList);

    List<GirlsRoom> assigningBedToGirlsStudent(int id, List<GirlsRoom> girlsRoomList);
     void bedRequestOperation(int id) throws HostelNotFoundException;
}
