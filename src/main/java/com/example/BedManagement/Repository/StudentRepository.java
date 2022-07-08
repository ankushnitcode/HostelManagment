package com.example.BedManagement.Repository;

import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StudentRepository extends JpaRepository<StudentInfo,Integer> {


    @Override
    <S extends com.example.BedManagement.Model.StudentInfo> S save(S Entity);
}
