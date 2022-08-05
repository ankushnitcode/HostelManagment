package com.example.BedManagement.repository;

import com.example.BedManagement.entity.Student;
import com.example.BedManagement.repository.StudentRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentRepositoryTest {
    @Autowired private StudentRepository studentRepository;
    @Autowired private TestEntityManager entityManager;

//    @Test
//   public void existsByEmail(){
//        Student student = new Student(1,"Abc",null);
//        entityManager.merge(student);// used merge instead of persist to solve PersistenceException with the “detached entity passed to persist” error message.
//        boolean actual = studentRepository.(student.getEmail());
//        assertThat(actual).isTrue();
//    }
}
