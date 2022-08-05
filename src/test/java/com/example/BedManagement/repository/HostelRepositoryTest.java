package com.example.BedManagement.repository;

import com.example.BedManagement.entity.Hostel;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

//@DataJpaTest
//@ExtendWith(SpringExtension.class)
//@SpringBootTest(classes = HostelRepository.class)
@RunWith(SpringRunner.class)
//@WebMvcTest(HostelRepository.class)
@SpringBootTest(classes = HostelRepository.class)
class HostelRepositoryTest {
    @Autowired private HostelRepository hostelRepository;
    @Autowired private DataSource dataSource;
    @Autowired private JdbcTemplate jdbcTemplate;
    @Autowired private EntityManager entityManager;

    @Test
   public void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(jdbcTemplate).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(hostelRepository).isNotNull();
    }
    @Test
     void existsHostelCanBeFound(){
        Hostel hostel = new Hostel(1, "Male");
        hostelRepository.save(hostel);
        assertThat(hostelRepository.findById(1).get().getHostelNumber()).isEqualTo(1);
    }

//    @Test
//    void findHostelByHostelName(){
//        Hostel hostel = new Hostel(1, Gender.FEMALE, HostelName.RED);
//        hostelRepository.save(hostel);
//        Hostel actual = hostelRepository.findByHostelName(HostelName.RED);
//        assertThat(actual).isNotNull();
//    }

}