package com.example.BedManagement.entity;


import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;


@Entity
@Table(name = "student")
@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int studentId;
    @NotNull
    private String studentName;
    private String studentGender;
    private Boolean haveBed;
    @ManyToOne
    private BoysRoom boysRoom;
    @ManyToOne
    private GirlsRoom girlsRoom;

    public Student(String studentName, String studentGender, Boolean haveBed) {
        this.studentName = studentName;
        this.studentGender = studentGender;
        this.haveBed = haveBed;
    }


}
