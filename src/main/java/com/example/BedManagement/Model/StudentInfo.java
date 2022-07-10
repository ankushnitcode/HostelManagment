package com.example.BedManagement.Model;


import com.example.BedManagement.Entity.Room;
import com.example.BedManagement.Entity.Student;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StudentInfo {
    private Integer Id;
    private String studentName;
    private String studentGender;
    private  Boolean haveBed;



//    public void create(){
//
//        Student s1 = new Student(1,"abc","male",true,new Room());
//    }
}
