package com.example.demo.repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


import com.example.demo.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	@Query("SELECT p FROM Patient p WHERE p.email = :email")
   Optional<User> findByEmail(@Param("email") String email);
	
}
