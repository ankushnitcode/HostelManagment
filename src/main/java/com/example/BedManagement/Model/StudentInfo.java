package com.example.BedManagement.Model;


import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
public class StudentInfo {
    @Id
    private int StudentId;
    private String name;
    private String gender;
    private  Boolean haveBed;
    private int roomId;

}
