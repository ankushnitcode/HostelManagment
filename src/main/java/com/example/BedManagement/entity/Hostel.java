package com.example.BedManagement.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Hostel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hostelNumber;
    private String hostelCategory;


   @OneToMany(cascade = CascadeType.ALL)
   @LazyCollection(LazyCollectionOption.FALSE)
   private List<BoysRoom> boysRoomList;


    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
   private List<GirlsRoom> girlsRoomList;

    public Hostel(int hostelNumber) {
        this.hostelNumber = hostelNumber;
    }
}
