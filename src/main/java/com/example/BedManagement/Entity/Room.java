package com.example.BedManagement.Entity;

import com.example.BedManagement.Model.StudentInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Room {
    @Id
    private int roomId;
    @OneToMany
   private List<StudentInfo> studentList;
   // @ManyToOne
    //private Hostel hostel;
}
