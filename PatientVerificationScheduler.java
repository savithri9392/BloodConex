package com.example.demo.scheduler;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;

import com.example.demo.service.HashingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class PatientVerificationScheduler {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private HashingService hashService;

    // ✅ Store errors in memory (or use a database)
    private final ConcurrentHashMap<Integer, String> verificationErrors = new ConcurrentHashMap<>();

    @Scheduled(cron = "0 0 0 * * ?")  // Runs every day at midnight
    public void verifyPatientDataDaily() {
        List<Patient> patients = patientRepository.findAll();

        verificationErrors.clear(); // Clear previous errors

        for (Patient patient : patients) {
            String recalculatedHash = hashService.generateHash(
                patient.getFirstName() + patient.getLastName() + patient.getGender() +
                patient.getDob() + patient.getAge() + patient.getBloodGroup() +
                patient.getAddress() + patient.getState() + patient.getCity() +
                patient.getPhone() + patient.getEmail() + patient.getPasswordHash()
            );

            if (!recalculatedHash.equals(patient.getDataHash())) {
                verificationErrors.put(patient.getId(), "Hash mismatch for Patient ID: " + patient.getId());
            }
        }

        if (verificationErrors.isEmpty()) {
            System.out.println("✅ All patient data hashes verified successfully!");
        } else {
            System.out.println("❌ Hash mismatches found: " + verificationErrors.size());
        }
    }

    public ConcurrentHashMap<Integer, String> getVerificationErrors() {
        return verificationErrors;
    }
}
