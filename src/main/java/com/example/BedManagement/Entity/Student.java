package com.example.BedManagement.Entity;

import com.example.BedManagement.Model.StudentInfo;
import lombok.*;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class Student {
    @Id
    private int studentId;
    private String studentName;
    private String studentGender;
    private  Boolean haveBed;
    @ManyToOne
    private Room room;

    private static List<StudentInfo> students = new ArrayList<>();
    private static int usersCount = 3;

    static {
        students.add(new StudentInfo(1, "Adam", "Male",false));
        students.add(new StudentInfo(2, "Eve", "Male",false));
        students.add(new StudentInfo(3, "Jack", "Male",false));
    }
   public List<StudentInfo> findAll() {
    return students;
}

    public StudentInfo save(StudentInfo student) {
        if (student.getId() == null) {
            student.setId(++usersCount);
        }
        students.add(student);
        return student;
    }

    public StudentInfo findOne(int id) {
        for (StudentInfo student : students) {
            if (student.getId() == id) {
                return student;
            }
        }
        return null;
    }

}
