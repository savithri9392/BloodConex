package com.example.demo.controller;

import com.example.demo.entity.Patient;
import com.example.demo.scheduler.PatientVerificationScheduler;
import com.example.demo.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private PatientVerificationScheduler scheduler;
    // Create a new patient-signup.html
    @PostMapping
    public ResponseEntity<?> createPatient(@RequestBody Patient patient) {
        
     
        Patient createdPatient = patientService.createPatient(patient);
        return ResponseEntity.ok(createdPatient);
      
    }

    // Get all patients-viewdonor.html,leaderboard.html
    @GetMapping("/patientdata")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        return ResponseEntity.ok(patients);
    }
    
  //bloodstock.html
    @GetMapping("/blood-group-count")
    public List<Object[]> getBloodGroupCount() {
        return patientService.getBloodGroupCounts();
    }
 // Update an existing patient-profile.html
    @PutMapping("/{email}")
    public ResponseEntity<?> updatePatient(
            @PathVariable String email, 
            @RequestBody Patient updatedPatient) {
    	
        Patient patient = patientService.updatePatient(email, updatedPatient);
        return ResponseEntity.ok(patient);
    }
   
    //contribution.html
    @PutMapping("/{email}/update-cpt")
    public ResponseEntity<?> updateCpt(@PathVariable String email, @RequestBody Map<String, Integer> requestBody) {
        if (!requestBody.containsKey("cpt")) {
            return ResponseEntity.badRequest().body("Missing 'cpt' field in request");
        }

        int newCpt = requestBody.get("cpt");
        Patient updatedPatient = patientService.updateCpt(email, newCpt);
        return ResponseEntity.ok(updatedPatient);
    }

    // Get a patient by ID
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Integer id) {
        Patient patient = patientService.getPatientById(id);
        return ResponseEntity.ok(patient);
    }
    
    

    

    
    // Delete a patient
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Integer id) {
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
    //search.html

    @GetMapping("/search")
    public List<Patient> searchDonors(@RequestParam String state, 
                                      @RequestParam String city, 
                                      @RequestParam String bloodGroup) {
        return patientService.getPatientsByStateCityAndBloodGroup(state, city, bloodGroup);}
    //security
    @GetMapping("/errors")
    public Map<Integer, String> getVerificationErrors() {
        return scheduler.getVerificationErrors();
    }
}