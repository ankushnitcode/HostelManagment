package com.example.BedManagement.services;

import com.example.BedManagement.controllers.StudentDetailsController;
import com.example.BedManagement.controllers.StudentRegistrationAndRequestController;
import com.example.BedManagement.entity.Student;
import com.example.BedManagement.repository.BoysRoomRepository;
import com.example.BedManagement.repository.GirlsRoomRepository;
import com.example.BedManagement.repository.HostelRepository;
import com.example.BedManagement.repository.StudentRepository;
import com.example.BedManagement.services.Impl.StudentRegisterServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentDetailsController.class)
//@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentRegisterServiceImplTest {
    @MockBean
    StudentDetailsController studentDetailController;
    @InjectMocks
    StudentRegistrationAndRequestController studentRegistrationAndRequestController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    StudentRegisterServiceImpl studentRegisterService;
    @MockBean
    HostelRepository hostelRepository;
    @MockBean
    StudentRepository studentRepository;
    @MockBean
    BoysRoomRepository boysRoomRepository;
    @MockBean
    GirlsRoomRepository girlsRoomRepository;

    @Test
    public void createNewStudent_CreatingNewStudent_StudentCreatedReponse() throws Exception {
        String uri = "/HostelSystem/students";
        Student student = new Student();
        student.setStudentId(1);
        student.setStudentName("Ginger");
        String inputJson = this.mapToJson(student);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
    }

    @Test
    public void assigningRoomToHostel_hostelRepositoryNotNull_RoomAssigned(){
        Student student = new Student();
        student.setStudentId(1);
        student.setStudentName("Chintu");
        studentRepository.save(student);
        studentRegisterService.bedRequestOperation(1);
        assertNotNull(hostelRepository.findById(1));
    }

    @Test
    public void assigningBedToBoyStudent_GivingRoomToBoy_RoomAssigned(){
        Student student = new Student();
        student.setStudentName("QWSA");
        student.setStudentId(1);
        studentRepository.save(student);
        studentRegisterService.assigningBedToBoyStudent(1);

    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
