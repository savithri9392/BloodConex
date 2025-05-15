package com.example.demo.controller;


import com.example.demo.entity.ContactUs;
import com.example.demo.service.ContactUsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin("*")  // Allow frontend to call API
public class ContactUsController {

    @Autowired
    private ContactUsService contactUsService;

    // Submit Contact Us form
    @PostMapping("/submit")
    public ContactUs submitQuery(@RequestBody ContactUs contact) {
        return contactUsService.saveQuery(contact);
    }

    // Get all submitted queries
    @GetMapping("/all")
    public List<ContactUs> getAllQueries() {
        return contactUsService.getAllQueries();
    }
}