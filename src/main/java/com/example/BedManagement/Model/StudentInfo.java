package com.example.BedManagement.Model;


import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentInfo {
    private Integer Id;
    private String name;
    private String gender;
    private  Boolean haveBed;



//    public void create(){
//
//        Student s1 = new Student(1,"abc","male",true,new Room());
//    }
}
