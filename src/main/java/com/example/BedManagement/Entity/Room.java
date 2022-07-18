package com.example.BedManagement.Entity;

import com.example.BedManagement.Model.StudentInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;
    @OneToMany
   private List<StudentInfo> studentList;
    @ManyToOne
    private Hostel hostel;

    public Room(int roomId, List<StudentInfo> studentList) {
        this.roomId = roomId;
        this.studentList = studentList;
    }

    public Room(List<StudentInfo> studentList) {
        this.studentList = studentList;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public List<StudentInfo> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<StudentInfo> studentList) {
        this.studentList = studentList;
    }


}
