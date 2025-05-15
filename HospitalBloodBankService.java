package com.example.demo.service;

import com.example.demo.entity.HospitalBloodBank;
import com.example.demo.repository.HospitalBloodBankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HospitalBloodBankService {

    @Autowired
    private HospitalBloodBankRepository hospitalBloodBankRepository;

    // Save a new hospital/blood bank
    public HospitalBloodBank saveHospitalBloodBank(HospitalBloodBank hospitalBloodBank) {
        return hospitalBloodBankRepository.save(hospitalBloodBank);
    }

    // Get all hospitals/blood banks
    public List<HospitalBloodBank> getAllHospitalBloodBanks() {
        return hospitalBloodBankRepository.findAll();
    }

    // Get hospital/blood bank by ID
    public Optional<HospitalBloodBank> getHospitalBloodBankById(Long id) {
        return hospitalBloodBankRepository.findById(id);
    }

    // Get hospitals/blood banks by state and city
    public List<HospitalBloodBank> getByStateAndCity(String state, String city) {
        return hospitalBloodBankRepository.findByStateAndCity(state, city);
    }

    // Update hospital/blood bank details
    public HospitalBloodBank updateHospitalBloodBank(Long id, HospitalBloodBank updatedHospitalBloodBank) {
        return hospitalBloodBankRepository.findById(id)
                .map(existingHospital -> {
                    existingHospital.setName(updatedHospitalBloodBank.getName());
                   
                    existingHospital.setAddress(updatedHospitalBloodBank.getAddress());
                    existingHospital.setCity(updatedHospitalBloodBank.getCity());
                    existingHospital.setState(updatedHospitalBloodBank.getState());
                    existingHospital.setContactNumber(updatedHospitalBloodBank.getContactNumber());
                    existingHospital.setEmail(updatedHospitalBloodBank.getEmail());
                    existingHospital.setWebsite(updatedHospitalBloodBank.getWebsite());
                    existingHospital.setLatitude(updatedHospitalBloodBank.getLatitude());
                    existingHospital.setLongitude(updatedHospitalBloodBank.getLongitude());
                    return hospitalBloodBankRepository.save(existingHospital);
                })
                .orElseThrow(() -> new RuntimeException("Hospital or Blood Bank not found with ID: " + id));
    }

    // Delete hospital/blood bank by ID
    public void deleteHospitalBloodBank(Long id) {
        hospitalBloodBankRepository.deleteById(id);
    }
}