package com.example.BedManagement.Repository;

import com.example.BedManagement.Model.StudentInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentInfoRepository extends JpaRepository<StudentInfo,Integer> {
}
