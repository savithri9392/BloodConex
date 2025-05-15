package com.example.demo.controller;

import com.example.demo.entity.HospitalBloodBank;
import com.example.demo.service.HospitalBloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/hospitals-bloodbanks")
@CrossOrigin(origins = "*") // Allow frontend requests
public class HospitalBloodBankController {

    @Autowired
    private HospitalBloodBankService hospitalBloodBankService;
 // Get hospitals/blood banks by state and city
    @GetMapping("/search")
    public List<HospitalBloodBank> getByStateAndCity(@RequestParam String state, @RequestParam String city) {
        return hospitalBloodBankService.getByStateAndCity(state, city);
    }

    // Create a new hospital/blood bank
    @PostMapping
    public HospitalBloodBank addHospitalBloodBank(@RequestBody HospitalBloodBank hospitalBloodBank) {
        return hospitalBloodBankService.saveHospitalBloodBank(hospitalBloodBank);
    }

    // Get all hospitals/blood banks
    @GetMapping("/list-all")
    public List<HospitalBloodBank> getAllHospitalBloodBanks() {
        return hospitalBloodBankService.getAllHospitalBloodBanks();
    }

    // Get a hospital/blood bank by ID
 
    

    // Update hospital/blood bank
    @PutMapping("/{id}")
    public HospitalBloodBank updateHospitalBloodBank(@PathVariable Long id, @RequestBody HospitalBloodBank updatedHospitalBloodBank) {
        return hospitalBloodBankService.updateHospitalBloodBank(id, updatedHospitalBloodBank);
    }

    
}