package com.example.demo.repository;

import com.example.demo.entity.Patient;
import com.example.demo.entity.Request;

import java.util.List;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	 @Query("SELECT p FROM Patient p WHERE p.email = :email")
	    Optional<Patient> findByEmail(@Param("email") String email);
	 
	 @Query("SELECT p FROM Patient p WHERE p.state LIKE %:state% AND p.city LIKE %:city% AND p.bloodGroup LIKE %:bloodGroup% AND p.eligibility = 'Yes'")

	    List<Patient> findByStateAndCityAndBloodGroup(@Param("state") String state, 
	                                                  @Param("city") String city, 
	                                                  @Param("bloodGroup") String bloodGroup);
	 
	 @Query("SELECT p FROM Patient p WHERE p.id = :donorId ")
	 Patient findEmailByDonorId(@Param("donorId") Integer donorId);
	
	 @Query("SELECT p FROM Patient p WHERE p.email = :email")
	    Patient findByEmails(@Param("email") String email);
	 
	 @Query("SELECT d.state, d.city, d.bloodGroup, COUNT(d) as bloodCount " +
		       "FROM Patient d " +
		       "WHERE d.bloodGroup IN ('A positive', 'A negative', 'B positive', 'B negative', " +
		       "'O positive', 'O negative', 'AB positive', 'AB negative') " +
		       "GROUP BY d.state, d.city, d.bloodGroup " +
		       "ORDER BY d.state ASC, d.city ASC, bloodCount DESC")
		List<Object[]> countByBloodGroupCityState();
	
}