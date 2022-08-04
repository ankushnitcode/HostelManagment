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
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GirlsRoom {
    @ApiModelProperty(value = "GirlsRoomId")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int roomId;
    @OneToMany(cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Student> studentList;
    @ManyToOne
    private Hostel hostel;

}
