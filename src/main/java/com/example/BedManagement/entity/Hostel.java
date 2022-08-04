package com.example.BedManagement.entity;


import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "HostelNo")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int hostelNumber;
    @ApiModelProperty(value = "HostelCategory")
    private String hostelCategory;

    @ApiModelProperty(value = "boysRoomList")
   @OneToMany(cascade = CascadeType.ALL)
   @LazyCollection(LazyCollectionOption.FALSE)
   private List<BoysRoom> boysRoomList;

    @ApiModelProperty(value = "GirlsRoomList")
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
   private List<GirlsRoom> girlsRoomList;

    public Hostel(int hostelNumber) {
        this.hostelNumber = hostelNumber;
    }
}
