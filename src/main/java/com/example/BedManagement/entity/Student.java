package com.example.BedManagement.entity;


import com.sun.istack.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import javax.persistence.*;

@ApiModel(description = "")
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
    @ApiModelProperty(value = "StudentId")
    private int studentId;
    @ApiModelProperty(value = "StudentName",required = true)
    @NotNull
    private String studentName;
    @ApiModelProperty(value = "StudentGender",required = true)
    private String studentGender;
    @ApiModelProperty(value = "StudentHaveBed",required = true)
    private Boolean haveBed;
    @ApiModelProperty(value = "BoysRoom")
    @ManyToOne
    private BoysRoom boysRoom;
    @ApiModelProperty(value = "GirlsRoom")
    @ManyToOne
    private GirlsRoom girlsRoom;

    public Student(String studentName, String studentGender, Boolean haveBed) {
        this.studentName = studentName;
        this.studentGender = studentGender;
        this.haveBed = haveBed;
    }


}
