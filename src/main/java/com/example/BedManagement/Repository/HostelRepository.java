package com.example.BedManagement.Repository;

import com.example.BedManagement.Entity.Hostel;
import com.example.BedManagement.Entity.BoysRoom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface HostelRepository extends JpaRepository<Hostel,Integer>  {

}
