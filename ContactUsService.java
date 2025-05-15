package com.example.demo.service;


import com.example.demo.entity.ContactUs;
import com.example.demo.repository.ContactUsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactUsService {

    @Autowired
    private ContactUsRepository contactUsRepository;

    // Save contact query
    public ContactUs saveQuery(ContactUs contact) {
        return contactUsRepository.save(contact);
    }

    // Get all queries
    public List<ContactUs> getAllQueries() {
        return contactUsRepository.findAll();
    }
}