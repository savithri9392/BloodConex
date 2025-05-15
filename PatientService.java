package com.example.demo.service;

import com.example.demo.entity.Patient;
import com.example.demo.repository.PatientRepository;

import jakarta.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;
    
    private static final Logger logger = LoggerFactory.getLogger(PatientService.class);


    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(int id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        
    }

    public Patient createPatient(Patient patient) {
    	logger.info(patient.toString());
        return patientRepository.save(patient);
    }

    public Patient updatePatient(String email, Patient updatedPatient) {
        Patient existingPatient = getPatientByEmail(email);
        
        existingPatient.setFirstName(updatedPatient.getFirstName() != null ? updatedPatient.getFirstName() : existingPatient.getFirstName());
        existingPatient.setLastName(updatedPatient.getLastName() != null ? updatedPatient.getLastName() : existingPatient.getLastName());
        existingPatient.setGender(updatedPatient.getGender() != null ? updatedPatient.getGender() : existingPatient.getGender());
        existingPatient.setDob(updatedPatient.getDob() != null ? updatedPatient.getDob() : existingPatient.getDob());
        existingPatient.setAge(updatedPatient.getAge() != null ? updatedPatient.getAge() : existingPatient.getAge());
        existingPatient.setBloodGroup(updatedPatient.getBloodGroup() != null ? updatedPatient.getBloodGroup() : existingPatient.getBloodGroup());
        existingPatient.setAddress(updatedPatient.getAddress() != null ? updatedPatient.getAddress() : existingPatient.getAddress());
        existingPatient.setState(updatedPatient.getState() != null ? updatedPatient.getState() : existingPatient.getState());
        existingPatient.setCity(updatedPatient.getCity() != null ? updatedPatient.getCity() : existingPatient.getCity());
        existingPatient.setPhone(updatedPatient.getPhone() != null ? updatedPatient.getPhone() : existingPatient.getPhone());
        existingPatient.setEmail(updatedPatient.getEmail() != null ? updatedPatient.getEmail() : existingPatient.getEmail());
        existingPatient.setPasswordHash(updatedPatient.getPasswordHash() != null ? updatedPatient.getPasswordHash() : existingPatient.getPasswordHash());

        // Recalculate and update the hash value
        String newHash = hashSHA256(
            existingPatient.getFirstName() + existingPatient.getLastName() + existingPatient.getGender() +
            existingPatient.getDob() + existingPatient.getAge() + existingPatient.getBloodGroup() +
            existingPatient.getAddress() + existingPatient.getState() + existingPatient.getCity() +
            existingPatient.getPhone() + existingPatient.getEmail() + existingPatient.getPasswordHash()
        );

        existingPatient.setDataHash(newHash);

        return patientRepository.save(existingPatient);
    }

    public Patient getPatientByEmail(String email) {
        return patientRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }
	public void deletePatient(Integer id) {
        patientRepository.deleteById(id);
    }
    public List<Patient> getPatientsByStateCityAndBloodGroup(String state, String city, String bloodGroup) {
        logger.info("Fetching patients for state: {}, city: {}, blood group: {}", state, city, bloodGroup);
        
        List<Patient> patients = patientRepository.findByStateAndCityAndBloodGroup(state, city, bloodGroup);
        
        // Log the result
        if (patients.isEmpty()) {
            logger.info("No patients found for state: {}, city: {}, blood group: {}", state, city, bloodGroup);
        } else {
            logger.info("Found {} patients for state: {}, city: {}, blood group: {}", patients.size(), state, city, bloodGroup);
        }
        
        return patients;
    }
    public List<Object[]> getBloodGroupCounts() {
        return patientRepository.countByBloodGroupCityState();
    }
    public boolean verifyPatientData(Patient patient) {
        String recalculatedHash = hashSHA256(
            patient.getFirstName() + patient.getLastName() + patient.getGender() +
            patient.getDob() + patient.getAge() + patient.getBloodGroup() +
            patient.getAddress() + patient.getState() + patient.getCity() +
            patient.getPhone() + patient.getEmail() + patient.getPasswordHash()
        );
        return recalculatedHash.equals(patient.getDataHash());
    }
    @Transactional
    public Patient updateCpt(String email, int newCpt) {
        Patient existingPatient = patientRepository.findByEmail(email)
            .orElseThrow(() -> new RuntimeException("Patient not found with email: " + email));

        existingPatient.setCpt(newCpt);

        // ✅ Recalculate hash after updating cpt
        String newHash = hashSHA256(
            existingPatient.getFirstName() + existingPatient.getLastName() + existingPatient.getGender() +
            existingPatient.getDob() + existingPatient.getAge() + existingPatient.getBloodGroup() +
            existingPatient.getAddress() + existingPatient.getState() + existingPatient.getCity() +
            existingPatient.getPhone() + existingPatient.getEmail() + existingPatient.getPasswordHash()
        );
        existingPatient.setDataHash(newHash);

        return patientRepository.save(existingPatient);
    }
    private String hashSHA256(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                hexString.append(String.format("%02x", b));
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error generating hash", e);
        }
    }
}