package com.example.demo.controller;

import java.time.LocalDateTime;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.messaging.handler.annotation.MessageMapping;
import com.example.demo.entity.Request;
import com.example.demo.service.RequestService;

@RestController
@RequestMapping("/api/requests")
public class RequestController {

    @Autowired
    private RequestService requestService;
    
    private final SimpMessagingTemplate messagingTemplate;

    public RequestController( SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate =messagingTemplate;
    }

//search.html-request btn
    @PostMapping("/send")
    public ResponseEntity<String> saveRequest(@RequestBody Request request) {
    	  
          request.setStatus("Pending");
          requestService.saveRequest(request);
          
         
          return ResponseEntity.ok("Request Sent Successfully");
        
    }
    //notification.html
    @GetMapping("/donor/{email}")
    public List<Request> getRequests(@PathVariable String email) {
        return requestService.getRequestsByDonorEmail(email);
    }
    //history.html
    @GetMapping("/sender/{email}")
    public List<Request> getSenderRequests(@PathVariable String email) {
        return requestService.getRequestsBySenderEmail(email);
    }

    @PostMapping("/updateStatus")
    public ResponseEntity<String> updateRequestStatus(@RequestParam int requestId, @RequestParam String status) {
        requestService.updateRequestStatus(requestId, status);
        return ResponseEntity.ok("Request Updated Successfully");
    }

   //requesthistory.html-admin page
	 @GetMapping("/Requestdata")
	    public ResponseEntity<List<Request>> getAllPatients() {
	        List<Request> patients = requestService.getAllPatients();
	        return ResponseEntity.ok(patients);
	    }
	 @GetMapping("/latest/{donorEmail}")
	 public Request getLatestRequest(@PathVariable String donorEmail) {
	     return requestService.getLatestRequestByEmail(donorEmail);
	 }

    
    
    
}