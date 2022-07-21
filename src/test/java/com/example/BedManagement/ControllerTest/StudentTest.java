package com.example.BedManagement.ControllerTest;

import com.example.BedManagement.Repository.StudentRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
//@WebMvcTest(StudentDetailsController.class)
@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentTest {
    @Autowired
    private MockMvc mockMvc;
    @InjectMocks
    StudentRepository studentRepository;

    @Test
   // @Order(5)
    public void deleteStudent(){
        studentRepository.deleteById(26);
        assertThat(studentRepository.existsById(26)).isFalse();
    }
}
