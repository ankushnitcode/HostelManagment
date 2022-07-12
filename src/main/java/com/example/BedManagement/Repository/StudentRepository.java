package com.example.BedManagement.Repository;

import com.example.BedManagement.Model.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface StudentRepository extends JpaRepository<StudentInfo , Integer> {
    public List<StudentInfo> studentName(String name);
    public List<StudentInfo> findByAge(int Id);
    public StudentInfo findByEmail(String Gender);

    @Query(value = "SELECT e FROM Employee e ORDER BY name")
    public List<StudentInfo> findAllSortedByName();

    @Query(value = "SELECT * FROM Employee ORDER BY name", nativeQuery = true)
    public List<StudentInfo> findAllSortedByNameUsingNative();


    @Override
    <S extends com.example.BedManagement.Model.StudentInfo> S save(S Entity);
}
