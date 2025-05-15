package com.example.demo.service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Patient;
import com.example.demo.entity.Request;
import com.example.demo.repository.PatientRepository;
import com.example.demo.repository.RequestRepository;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private PatientRepository patientRepository; 
    private final SimpMessagingTemplate messagingTemplate; 
    
    public RequestService(RequestRepository requestRepository, PatientRepository patientRepository, SimpMessagingTemplate messagingTemplate) {
        this.requestRepository = requestRepository;
        this.patientRepository = patientRepository;
        this.messagingTemplate = messagingTemplate;
    }// Assuming you have a PatientRepository

    public Request saveRequest(Request request) {
        // Convert donor ID to donor Email
        Patient request2 = patientRepository.findEmailByDonorId(Integer.parseInt(request.getDonorId()));
        request.setcreatedAt(LocalDateTime.now());
        // Replace donorId with email
        request.setDonorEmail(request2.getEmail());
        request.setDonorName(request2.getFirstName());
        request.setDonorPhone(request2.getPhone());
        request.setDonorBloodgroup(request2.getBloodGroup());
        request.setAddress(request2.getAddress());
        request.setMessage("You have a blood request");
   
        return requestRepository.save(request);
    }
    public List<Request> getRequestsByDonorEmail(String donorEmail) {
        return requestRepository.findEmailByDonorEmail(donorEmail);
    }
    public List<Request> getRequestsBySenderEmail(String donorEmail) {
        return requestRepository.findEmailBySenderEmail(donorEmail);
    }
        public void updateRequestStatus(int requestId, String status) {
        	Request request = requestRepository.findById(requestId)
                    .orElseThrow(() -> new RuntimeException("Request not found"));
            request.setStatus(status);
            requestRepository.save(request);

            if ("Accepted".equalsIgnoreCase(status)) {
            	String donorEmail = request.getDonorEmail();
                Patient donor = patientRepository.findByEmails(donorEmail);
                Integer sum=donor.getCpt()+100;
                donor.setCpt(sum);
                donor.setEligibility("No");
                patientRepository.save(donor);
            } else if ("Rejected".equalsIgnoreCase(status)) {
                requestRepository.deleteById(requestId);
            }
         
        }
        
        public List<Request> getAllPatients() {
            return requestRepository.findAll();
        }
        public Request getLatestRequestByEmail(String donorEmail) {
            return requestRepository.findLatestRequestByEmail(donorEmail);
        }
        // 🔹 Method to check and update eligibility after 3 months
        public void checkAndUpdateEligibility(Patient donor) {
            if (donor.getDonationDate() != null) {
                LocalDate lastDonationDate = donor.getDonationDate();
                LocalDate threeMonthsLater = lastDonationDate.plusMonths(3);
                
                if (LocalDate.now().isAfter(threeMonthsLater) && donor.getEligibility().equals("No")) {
                    donor.setEligibility("Yes");
                    patientRepository.save(donor);
                    System.out.println("✅ Eligibility updated to YES for donor: " + donor.getEmail());
                }
            }
        }

        // 🔹 Scheduled Task to check eligibility daily at midnight
        @Scheduled(cron = "0 0 0 * * ?") // Runs every day at 12:00 AM
        public void checkEligibilityForAllDonors() {
            System.out.println("🔄 Running daily eligibility check...");
            List<Patient> donors = patientRepository.findAll(); // Get all patients
            
            for (Patient donor : donors) {
                checkAndUpdateEligibility(donor);
            }
            System.out.println("✅ Daily eligibility check completed.");
        }
        
    }