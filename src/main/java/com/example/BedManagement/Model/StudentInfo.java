package com.example.BedManagement.Model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class StudentInfo {
    private int studentId;
    private String studentName;
    private String studentGender;
    private  Boolean haveBed;

}
