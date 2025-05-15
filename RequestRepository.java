package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {

    @Query("SELECT p.email FROM Patient p WHERE p.id = :donorId")
    String findEmailByDonorId(@Param("donorId") Integer donorId);
    
    @Query("SELECT r FROM Request r WHERE r.donorEmail = :donorEmail")
    List<Request> findEmailByDonorEmail(@Param("donorEmail") String donorEmail);
    
    @Query("SELECT r FROM Request r WHERE r.donorEmail = :donorEmail ORDER BY r.createdAt DESC LIMIT 1")
    Request findLatestRequestByEmail(@Param("donorEmail") String donorEmail);
    
    @Query("SELECT r FROM Request r WHERE r.senderId = :senderId")
    List<Request> findEmailBySenderEmail(@Param("senderId") String senderId);
    
    
}