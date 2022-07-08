package com.example.BedManagement.Repository;

import com.example.BedManagement.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StudentRepository extends JpaRepository<Student,Integer> {


    @Override
    <S extends com.example.BedManagement.Entity.Student> S save(S entity);
}
