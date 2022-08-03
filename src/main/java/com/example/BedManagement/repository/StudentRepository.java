package com.example.BedManagement.repository;

import com.example.BedManagement.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StudentRepository extends JpaRepository<Student,Integer> {


    @Override
    <S extends com.example.BedManagement.entity.Student> S save(S Entity);
}
