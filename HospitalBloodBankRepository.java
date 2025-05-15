package com.example.demo.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.HospitalBloodBank;


@Repository
public interface HospitalBloodBankRepository extends JpaRepository<HospitalBloodBank, Long> {
	@Query("SELECT h FROM HospitalBloodBank h WHERE h.state LIKE %:state% AND h.city LIKE %:city%")
    List<HospitalBloodBank> findByStateAndCity(String state, String city);
}