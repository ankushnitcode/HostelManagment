package com.example.BedManagement.Model;


import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
public class StudentInfo {
    @Size(min = 3,message = "Name should be atleast 3 letters")
    private String name;
    private String gender;
    private  Boolean haveBed;

}
