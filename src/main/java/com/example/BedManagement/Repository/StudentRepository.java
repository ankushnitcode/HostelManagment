package com.example.BedManagement.Repository;

import com.example.BedManagement.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface StudentRepository extends JpaRepository<Student,Integer> {
   /* public List<StudentInfo> studentName(String name);
    public List<StudentInfo> findByAge(int Id);
    public StudentInfo findByEmail(String Gender);

    @Query(value = "SELECT e FROM Student e ORDER BY name")
    public List<StudentInfo> findAllSortedByName();

    @Query(value = "SELECT * FROM StudentInfo ORDER BY name", nativeQuery = true)
    public List<StudentInfo> findAllSortedByNameUsingNative();
*/

    @Override
    <S extends com.example.BedManagement.Entity.Student> S save(S Entity);
}
