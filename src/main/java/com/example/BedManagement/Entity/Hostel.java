package com.example.BedManagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hostel {
    @Id
    private int hostelNumber;
    private String hostelCategory;
    private String hostelName;
    private List<Room>roomList= new ArrayList<>(20);
}
