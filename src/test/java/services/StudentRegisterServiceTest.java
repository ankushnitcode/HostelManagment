package services;

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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(StudentDetailsController.class)
@SpringBootTest
//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class StudentRegisterServiceTest {
    @MockBean
    StudentDetailsController studentDetailController;
    @InjectMocks
    StudentRegistrationAndRequestController studentRegistrationAndRequestController;
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    StudentRegisterServiceImpl studentRegisterService;
    @MockBean
    HostelRepository hostelRepo;
    @MockBean
    StudentRepository studentRepo;
    @MockBean
    BoysRoomRepository boysRoomRepository;
    @MockBean
    GirlsRoomRepository girlsRoomRepository;

    @Test
    public void createNewStudent() throws Exception {
        String uri = "/HostelSystem/students";
        Student student = new Student();
        student.setStudentId(1);
        student.setStudentName("Ginger");

        String inputJson = this.mapToJson(student);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.post(uri)
                .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();

        int status = mvcResult.getResponse().getStatus();
        assertEquals(201, status);
        System.out.println(status + "@@@@@@@");
    }

    @Test
    public void assigingRoomToHostel(){
        Student student = new Student();
        student.setStudentId(1);
        student.setStudentName("Chintu");
        studentRepo.save(student);
       // System.out.println(student + "########");
       // studentRepo.findById(1).get();
        studentRegisterService.bedRequestOperation(1);
        //System.out.println(hostelRepo.findById(1) + "@@@@@@");
        assertNotNull(hostelRepo.findById(1));
    }

    @Test
    public void retriveAllStudent(){
       List<Student> response =  studentDetailController.retrieveAllStudent();
        assertEquals(studentRegisterService.findingAllStudent(),response);
    }

//    @Test
//    public void retriveStudentById(){
//        Student student = new Student();
//        student.setStudentId(1);
//        student.setStudentName("ABC");
//        student.setHaveBed(null);
//        studentRepo.save(student);
//    //studentDetailController.retrieveStudent(1);
//    assertEquals(true,studentRepo.findById(1));
//    }

//    @Test
//    public void retriveStudentByHostel(){
//        Student student = new Student();
//        student.setStudentId(1);
//        student.setStudentName("Chintu");
//        studentRepo.save(student);
//       int response = studentRegistrationAndRequestController.studentBedRequest(1).getStatusCodeValue();
//        //studentDetailController.retrieveStudentsByHostel(1);
//        //System.out.println(hostelRepo.existsById(2) + "{}{}{}{}{}");
//        assertEquals(200,response);
//    }

    //    @Test
//    public void SDCTest() throws Exception {
//        Student student = new Student();
//        student.setStudentName("TestKaTest");
//        student.setStudentGender("Male");
//        student.setHaveBed(null);
//        String inputInJSON = this.mapToJson(student);
//        String URI = "/HostelSystem/students/1";
//
//        Mockito.when(studentRegisterService.createNewStudent(Mockito.any(Student.class))).thenReturn(student);
//       // System.out.println(student + "@@@@@@@@@@@@@");
//        RequestBuilder request = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
//        MvcResult result = mockMvc.perform(request).andReturn();
//       // String expected = student//this.mapToJson(student);//String.valueOf(student);
//        String actual = result.getResponse().getContentAsString();
//        //System.out.println(actual + "#############");
//        System.out.println(student + "@@@@@@@@@@@@@");
//        System.out.println(result.getResponse().getContentAsString() + "#############");
//        assertEquals(student,actual);
//    }
    @Test
    public void findAllStudentTest(){
        when(studentRepo.findAll()).thenReturn(Stream.of
                        (new Student("Test1","Male",null),
                                new Student("Test2","Female",null))
                .collect(Collectors.toList()));
        assertThat(studentRepo).isNotNull();
        //assertEquals(2,service.getStudent().size());
    }

//    @Test
//    public void createNewStudent(){
//        Student newStudent = new Student();
//        newStudent.setStudentId(1);
//        newStudent.setStudentName("Test1");
//        newStudent.setStudentGender("Male");
//        newStudent.setHaveBed(null);
//        // newStudent.setRoom(null);
//        assertEquals(new Student("Test1","Male",null),newStudent);
//    }

    private String mapToJson(Object object) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(object);
    }

}
