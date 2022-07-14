package com.example.BedManagement.Model;

import com.example.BedManagement.Entity.Hostel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class HostelManager {
    List<Hostel>boysHostelList = new ArrayList<>(2);
    List<Hostel>girlsHostelList = new ArrayList<>(2);

    public List<Hostel> getBoysHostelList() {
        return boysHostelList;
    }

    public void setBoysHostelList(List<Hostel> boysHostelList) {
        this.boysHostelList = boysHostelList;
    }

    public List<Hostel> getGirlsHostelList() {
        return girlsHostelList;
    }

    public void setGirlsHostelList(List<Hostel> girlsHostelList) {
        this.girlsHostelList = girlsHostelList;
    }
}
