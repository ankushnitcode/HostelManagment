package com.example.BedManagement.Services.Impl;

import com.example.BedManagement.Entity.Room;
import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Model.StudentNotFoundException;
import com.example.BedManagement.Repository.RoomRepository;
import com.example.BedManagement.Repository.StudentInfoRepository;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentRegisterServiceImpl implements StudentRegisterService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    RoomRepository roomRepository;
@Autowired
    StudentInfoRepository studentInfoRepository;

    @Override
    public List<Student> findingAllStudent() {
        List<Student>studentInfoList = studentRepository.findAll();
        return studentInfoList;
    }
    public Student createNewStudent(StudentInfo studentInfo){
        Student newStudent = new Student();
        newStudent.setStudentName(studentInfo.getName());
        newStudent.setStudentGender(studentInfo.getGender());
        newStudent.setHaveBed(studentInfo.getHaveBed());
        return newStudent;
    }
    public StudentInfo createNewStudentInfo(Student student){
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setStudentId(student.getStudentId());
        studentInfo.setName(student.getStudentName());
        studentInfo.setHaveBed(student.getHaveBed());
        studentInfo.setRoomId(student.getRoomId());
        studentInfo.setGender(student.getStudentGender());
        return studentInfo;
    }
    public Student createStudentResponse(Student student){
        Student studentResponse = new Student();
        studentResponse.setStudentId(student.getStudentId());
        studentResponse.setStudentName(student.getStudentName());
        studentResponse.setStudentGender(student.getStudentGender());
        studentResponse.setHaveBed(student.getHaveBed());
        studentResponse.setRoomId(student.getRoomId());
        return studentResponse;
    }
    public void assigningBedToStudent(int id){
        Student assigningStudent = studentRepository.findById(id).get();
        List<Room>roomList= roomRepository.findAll();
        System.out.print("no of rooms is "+ roomList.size());
        if(roomList.size()>0){
            for (Room room : roomList) {
                if (room.getStudentList().size() < 4) {
                    assigningStudent.setHaveBed(true);
                    assigningStudent.setRoomId(room.getRoomId());
                    List<StudentInfo> newList = room.getStudentList();
                    StudentInfo assigningStudentInfo = createNewStudentInfo(assigningStudent);
                    newList.add(assigningStudentInfo);
                    room.setStudentList(newList);
                    studentInfoRepository.save(assigningStudentInfo);
                    studentRepository.save(assigningStudent);
                    roomRepository.save(room);
                    return;
                }
            }
            Room newRoom = new Room(roomList.size()+1,new ArrayList<>());
            assigningStudent.setHaveBed(true);
            assigningStudent.setRoomId(newRoom.getRoomId());
            List<StudentInfo>newList=   new ArrayList<>(4);
            StudentInfo assigningStudentInfo = createNewStudentInfo(assigningStudent);
            newList.add(assigningStudentInfo);
            newRoom.setStudentList(newList);
            studentInfoRepository.save(assigningStudentInfo);
            studentRepository.save(assigningStudent);
            roomRepository.save(newRoom);
        }
else{
                Room newRoom = new Room(roomList.size()+1,new ArrayList<>());
                assigningStudent.setHaveBed(true);
                assigningStudent.setRoomId(newRoom.getRoomId());
        List<StudentInfo>newList=   new ArrayList<>(4);
        StudentInfo assigningStudentInfo = createNewStudentInfo(assigningStudent);
        newList.add(assigningStudentInfo);
        newRoom.setStudentList(newList);
        studentInfoRepository.save(assigningStudentInfo);
                studentRepository.save(assigningStudent);
               roomRepository.save(newRoom);}
            }}







