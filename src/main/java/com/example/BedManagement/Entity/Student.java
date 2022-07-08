package com.example.BedManagement.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NonNull
public class Student {
    @Id
    private int studentId;
    private String studentName;
    private String studentGender;
    private  Boolean haveBed;


}
