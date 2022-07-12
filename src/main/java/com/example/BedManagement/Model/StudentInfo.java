package com.example.BedManagement.Model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentInfo {
    private String name;
    private String gender;
    private  Boolean haveBed;
}
