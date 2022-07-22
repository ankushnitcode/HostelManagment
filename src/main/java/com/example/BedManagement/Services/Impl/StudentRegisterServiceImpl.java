package com.example.BedManagement.Services.Impl;

import com.example.BedManagement.Entity.GirlsRoom;
import com.example.BedManagement.Entity.Hostel;
import com.example.BedManagement.Entity.BoysRoom;
import com.example.BedManagement.Entity.Student;
//import com.example.BedManagement.Model.HostelManager;
//import com.example.BedManagement.Model.RoomInfo;
//import com.example.BedManagement.Model.StudentInfo;
import com.example.BedManagement.Exception.HostelNotFoundException;
import com.example.BedManagement.Model.StudentNotFoundException;
import com.example.BedManagement.Repository.GirlsRoomRepository;
import com.example.BedManagement.Repository.HostelRepository;
import com.example.BedManagement.Repository.BoysRoomRepository;
//import com.example.BedManagement.Repository.StudentInfoRepository;
import com.example.BedManagement.Repository.StudentRepository;
import com.example.BedManagement.Services.StudentRegisterService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class StudentRegisterServiceImpl implements StudentRegisterService{
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    BoysRoomRepository boysRoomRepository;
@Autowired
 GirlsRoomRepository girlsRoomRepository;
   // StudentInfoRepository studentInfoRepository;
//@Autowired
   //  HostelManager hostelManager;
@Autowired
HostelRepository hostelRepository;

    @Override
    public List<Student> findingAllStudent() {
        List<Student>studentInfoList = studentRepository.findAll();
        return studentInfoList;
    }
    public Student createNewStudent(Student student){
        Student newStudent = new Student();
        newStudent.setStudentName(student.getStudentName());
        newStudent.setStudentGender(student.getStudentGender());
        newStudent.setHaveBed(student.getHaveBed());
        return newStudent;
    }/*
    public StudentInfo createNewStudentInfo(Student student){
        StudentInfo studentInfo = new StudentInfo();
        studentInfo.setStudentId(student.getStudentId());
        studentInfo.setName(student.getStudentName());
        studentInfo.setHaveBed(student.getHaveBed());
        //studentInfo.setRoomId(student.getRoomId());
        studentInfo.setGender(student.getStudentGender());
        return studentInfo;
    }*/
    public Student createStudentResponse(Student student){
        Student studentResponse = new Student();
        studentResponse.setStudentId(student.getStudentId());
        studentResponse.setStudentName(student.getStudentName());
        studentResponse.setStudentGender(student.getStudentGender());
        studentResponse.setHaveBed(student.getHaveBed());
       // studentResponse.setRoomId(student.getRoomId());
        return studentResponse;
    }
    public List<BoysRoom> assigningBedToStudent(int id, List<BoysRoom> roomList){
        Student assigningStudent = studentRepository.findById(id).get();
        List<BoysRoom> returningBoysRoomList = new ArrayList<>();
        if(roomList.size()>0){
            for (BoysRoom boysRoom : roomList) {
                if (boysRoom.getStudentList().size() < 4) {
                    assigningStudent.setHaveBed(true);
                  //  List<Student> newList = boysRoom.getStudentList();
                  //  newList.add(assigningStudent);
                  //  boysRoom.setStudentList(newList);
                    boysRoom.getStudentList().add(assigningStudent);
                   return roomList;

                }
            }
            BoysRoom room = new BoysRoom();
            assigningStudent.setHaveBed(true);
          List<Student>newList=   new ArrayList<>(4);
           newList.add(assigningStudent);
            room.setStudentList(newList);
          //  room.getStudentList().add(assigningStudent);
            roomList.add(room);
            return roomList;
        }
else{
                BoysRoom newBoysRoom = new BoysRoom();
                assigningStudent.setHaveBed(true);

        List<Student>newList=   new ArrayList<>(4);
//        newBoysRoom.getStudentList().add(assigningStudent);
         newList.add(assigningStudent);
       newBoysRoom.setStudentList(newList);
      roomList.add(newBoysRoom);
      returningBoysRoomList = roomList;

    }
         return returningBoysRoomList;
            }
    public List<GirlsRoom> assigningBedToGirlsStudent(int id, List<GirlsRoom> roomList){
        Student assigningStudent = studentRepository.findById(id).get();
        List<GirlsRoom> returningGirlsRoomList = new ArrayList<>();
        if(roomList.size()>0){
            System.out.println("@@@@@@@@@@@" +roomList);
            for (GirlsRoom girlsRoom : roomList) {
                if (girlsRoom.getStudentList().size() < 4) {
                    assigningStudent.setHaveBed(true);

                    List<Student> newList = girlsRoom.getStudentList();

                    newList.add(assigningStudent);
                    girlsRoom.setStudentList(newList);
                    returningGirlsRoomList = roomList;
                //   girlsRoomRepository.save(girlsRoom);
                    return returningGirlsRoomList;

                }
            }
            GirlsRoom room = new GirlsRoom();
            assigningStudent.setHaveBed(true);
            List<Student>newList=   new ArrayList<>(4);

            newList.add(assigningStudent);

            room.setStudentList(newList);


            roomList.add(room);
            return roomList;
        }
        else{
            GirlsRoom newGirlsRoom = new GirlsRoom();
            assigningStudent.setHaveBed(true);

            List<Student>newList=   new ArrayList<>(4);

            newList.add(assigningStudent);
            newGirlsRoom.setStudentList(newList);
            roomList.add(newGirlsRoom);
            returningGirlsRoomList = roomList;

        }
        return returningGirlsRoomList;
    }
            @Transactional
            public void assigningRoomToHostel(int id) throws HostelNotFoundException {
                Student student = studentRepository.findById(id).get();
                List<Hostel>hostelList= hostelRepository.findAll();
                List<Hostel>boysHostelList= new ArrayList<>();
                List<Hostel>girlsHostelList= new ArrayList<>();
                for(Hostel hostel:hostelList){
                    if (Objects.equals(hostel.getHostelCategory(), "Male"))
                        boysHostelList.add(hostel);
                    else
                        girlsHostelList.add(hostel);
                }
                if (Objects.equals(student.getStudentGender(), "Male")) {
                   // List<Hostel> hostelList = hostelManager.getBoysHostelList();

                    if (boysHostelList.size() == 0) {
                        Hostel newHostel = new Hostel();
                        newHostel.setHostelCategory(student.getStudentGender());
                        List<BoysRoom> boysRoomList = new ArrayList<>(20);
                      boysRoomList =   assigningBedToStudent(id, boysRoomList);

                      newHostel.setBoysRoomList(boysRoomList);

                        List<Hostel> newHostelList = new ArrayList<>();
                        newHostelList.add(newHostel);
                      //  hostelManager.setBoysHostelList(newHostelList);
                        hostelRepository.save(newHostel);
                        return;

                    } else {
                        for (Hostel hostel : boysHostelList) {
                            if (hostel.getBoysRoomList().size() < 20) {
                                List<BoysRoom> boysRoomList = hostel.getBoysRoomList();
                                System.out.println("***********" +boysRoomList);
                              boysRoomList =   assigningBedToStudent(id, boysRoomList);

                             //  hostel.setBoysRoomList(boysRoomList);


                             hostelRepository.save(hostel);
                             return;
                            }
                        }
                      if(boysHostelList.size()<2){
                        Hostel newHostel = new Hostel();
                        newHostel.setHostelCategory(student.getStudentGender());
                        List<BoysRoom> boysRoomList = new ArrayList<>(20);
                    boysRoomList =    assigningBedToStudent(id, boysRoomList);

                      newHostel.setBoysRoomList(boysRoomList);

                        List<Hostel> newHostelList = new ArrayList<>();
                        newHostelList.add(newHostel);
                       hostelRepository.save(newHostel);
                       return;}
                      else {
                          throw new HostelNotFoundException("Hostel already filled");
                      }
                    }

                }


                if (Objects.equals(student.getStudentGender(), "Female")){
                   // List<Hostel> hostelList = hostelManager.getGirlsHostelList();
                    if (girlsHostelList.size() == 0) {
                        Hostel hostel = new Hostel();
                        hostel.setHostelCategory(student.getStudentGender());
                        List<GirlsRoom> girlsRoomList = new ArrayList<>(20);
                     girlsRoomList =    assigningBedToGirlsStudent(id, girlsRoomList);

                      hostel.setGirlsRoomList(girlsRoomList);

                        List<Hostel> newHostelList = new ArrayList<>();
                        newHostelList.add(hostel);
                    //    hostelManager.setGirlsHostelList(newHostelList);
                       hostelRepository.save(hostel);
                       return;
                    } else {
                        for (Hostel hostel : girlsHostelList) {

                            if (hostel.getGirlsRoomList().size() < 20){

                            List<GirlsRoom> girlsRoomList = hostel.getGirlsRoomList();
                                System.out.println("***********" +girlsRoomList);
                       girlsRoomList =      assigningBedToGirlsStudent(id, girlsRoomList);

                          hostel.setGirlsRoomList(girlsRoomList);

                          hostelRepository.save(hostel);
                          return;}
                        }
                        if(girlsHostelList.size()<2) {
                            Hostel hostel = new Hostel();
                            hostel.setHostelCategory(student.getStudentGender());
                            List<GirlsRoom> girlsRoomList = new ArrayList<>(20);
                            girlsRoomList = assigningBedToGirlsStudent(id, girlsRoomList);

                            hostel.setGirlsRoomList(girlsRoomList);

                            List<Hostel> newHostelList = new ArrayList<>();
                            newHostelList.add(hostel);
                            hostelRepository.save(hostel);
                        }
                        else{
                            throw new HostelNotFoundException("Hostel already filled");
                        }
                    }
                }
            }
}














