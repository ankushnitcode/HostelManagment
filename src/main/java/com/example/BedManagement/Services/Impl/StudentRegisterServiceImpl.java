package com.example.BedManagement.Services.Impl;

import com.example.BedManagement.Entity.Hostel;
import com.example.BedManagement.Entity.Room;
import com.example.BedManagement.Entity.Student;
import com.example.BedManagement.Model.HostelManager;
//import com.example.BedManagement.Model.RoomInfo;
import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Model.StudentNotFoundException;
import com.example.BedManagement.Repository.HostelRepository;
import com.example.BedManagement.Repository.RoomRepository;
import com.example.BedManagement.Repository.StudentInfoRepository;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class StudentRegisterServiceImpl implements StudentRegisterService {
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    RoomRepository roomRepository;
@Autowired
    StudentInfoRepository studentInfoRepository;
@Autowired
     HostelManager hostelManager;
@Autowired
HostelRepository hostelRepository;

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
    public Room assigningBedToStudent(int id,List<Room>roomList){
        Student assigningStudent = studentRepository.findById(id).get();
        Room returningRoom = new Room();
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
                    returningRoom = room;
                    return returningRoom;
                }
            }
            Room newRoom = new Room(new ArrayList<>());
            assigningStudent.setHaveBed(true);

            List<StudentInfo>newList=   new ArrayList<>(4);
            StudentInfo assigningStudentInfo = createNewStudentInfo(assigningStudent);
            newList.add(assigningStudentInfo);
            newRoom.setStudentList(newList);
            studentInfoRepository.save(assigningStudentInfo);
            roomRepository.save(newRoom);
            assigningStudent.setRoomId(newRoom.getRoomId());
            studentRepository.save(assigningStudent);

            returningRoom = newRoom;
        }
else{
                Room newRoom = new Room(new ArrayList<>());
                assigningStudent.setHaveBed(true);

        List<StudentInfo>newList=   new ArrayList<>(4);
        StudentInfo assigningStudentInfo = createNewStudentInfo(assigningStudent);
        newList.add(assigningStudentInfo);
        newRoom.setStudentList(newList);


        studentInfoRepository.save(assigningStudentInfo);
            roomRepository.save(newRoom);
            assigningStudent.setRoomId(newRoom.getRoomId());
                studentRepository.save(assigningStudent);

               returningRoom = newRoom;

    }
         return returningRoom;
            }

            public void assigningRoomToHostel(int id) {
                Student student = studentRepository.findById(id).get();
                if (Objects.equals(student.getStudentGender(), "Male")) {
                    List<Hostel> hostelList = hostelManager.getBoysHostelList();
                    if (hostelList.size() == 0) {
                        Hostel newHostel = new Hostel();
                        newHostel.setHostelCategory(student.getStudentGender());
                        List<Room> roomList = new ArrayList<>(20);
                        Room room = assigningBedToStudent(id,roomList);
                        roomList.add(room);
                        newHostel.setRoomList(roomList);
                        List<Hostel> newHostelList = new ArrayList<>();
                        newHostelList.add(newHostel);
                        hostelManager.setBoysHostelList(newHostelList);
                        hostelRepository.save(newHostel);
                        return;

                    } else {
                        for (Hostel hostel : hostelList) {
                            if (hostel.getRoomList().size() < 20) {
                                List<Room> roomList = hostel.getRoomList();
                                Room room = assigningBedToStudent(id,roomList);
                                roomList.add(room);
                                hostel.setRoomList(roomList);
                             hostelRepository.save(hostel);
                             return;
                            }
                        }
                        Hostel newHostel = new Hostel();
                        newHostel.setHostelCategory(student.getStudentGender());
                        List<Room> roomList = new ArrayList<>(20);
                        Room room = assigningBedToStudent(id,roomList);
                        roomList.add(room);
                        newHostel.setRoomList(roomList);
                        List<Hostel> newHostelList = new ArrayList<>();
                        newHostelList.add(newHostel);
                       hostelRepository.save(newHostel);
                       return;
                    }

                }


                if (Objects.equals(student.getStudentGender(), "Female")){
                    List<Hostel> hostelList = hostelManager.getGirlsHostelList();
                    if (hostelList.size() == 0) {
                        Hostel hostel = new Hostel();
                        hostel.setHostelCategory(student.getStudentGender());
                        List<Room> roomList = new ArrayList<>(20);
                        Room room = assigningBedToStudent(id,roomList);
                        roomList.add(room);
                        hostel.setRoomList(roomList);
                        List<Hostel> newHostelList = new ArrayList<>();
                        newHostelList.add(hostel);
                        hostelManager.setGirlsHostelList(newHostelList);
                       hostelRepository.save(hostel);
                       return;
                    } else {
                        for (Hostel hostel : hostelList) {

                            if (hostel.getRoomList().size() < 20){

                            List<Room> roomList = hostel.getRoomList();
                            Room room = assigningBedToStudent(id,roomList);
                            roomList.add(room);
                            hostel.setRoomList(roomList);
                          hostelRepository.save(hostel);
                          return;}
                        }
                        Hostel hostel = new Hostel();
                        hostel.setHostelCategory(student.getStudentGender());
                        List<Room> roomList = new ArrayList<>(20);
                        Room room = assigningBedToStudent(id,roomList);
                        roomList.add(room);
                        hostel.setRoomList(roomList);
                        List<Hostel> newHostelList = new ArrayList<>();
                        newHostelList.add(hostel);
                       hostelRepository.save(hostel);

                    }
                }
            }
}














