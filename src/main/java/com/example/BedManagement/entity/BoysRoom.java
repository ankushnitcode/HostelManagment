package com.example.BedManagement.entity;

//import com.example.BedManagement.Model.StudentInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoysRoom {
    @ApiModelProperty(value = "BoysRoomId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;
    @ApiModelProperty(value = "studentList")
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Student> studentList;
    @ApiModelProperty(value = "Hostel")
    @ManyToOne
    private Hostel hostel;




    }




