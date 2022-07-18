package com.example.BedManagement.Model;

import com.example.BedManagement.Entity.Hostel;
import lombok.*;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@AllArgsConstructor
@Data
@Component
public class HostelManager {
    List<Hostel>boysHostelList = new ArrayList<>(2);
    List<Hostel>girlsHostelList = new ArrayList<>(2);

}
