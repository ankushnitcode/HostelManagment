package com.example.BedManagement.Controllers;

import com.example.BedManagement.Entity.Hostel;
import com.example.BedManagement.Model.HostelManager;
import com.example.BedManagement.Repository.HostelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class GivingHostelManagerAllTheHostelsList implements CommandLineRunner {
    @Autowired
    HostelRepository hostelRepository;
    @Autowired
    HostelManager hostelManager;

    @Override
    public void run(String... args) throws Exception {
        List<Hostel>hostelList= new ArrayList<>(4);
        hostelList = hostelRepository.findAll();
      for  (Hostel hostel : hostelList){
          if (Objects.equals(hostel.getHostelCategory(), "Male")){
              hostelManager.getBoysHostelList().add(hostel);}
          if (Objects.equals(hostel.getHostelCategory(), "Female")) {
              hostelManager.getGirlsHostelList().add(hostel);
          }

          }



    }
}
