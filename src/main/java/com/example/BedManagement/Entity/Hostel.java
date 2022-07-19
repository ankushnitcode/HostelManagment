package com.example.BedManagement.Entity;

//import com.example.BedManagement.Model.RoomInfo;
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
public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hostelNumber;
    private String hostelCategory;
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Room> roomList;

    public Hostel(int hostelNumber) {
        this.hostelNumber = hostelNumber;
    }
}
