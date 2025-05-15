package com.example.demo.controller;

import lombok.Data;
@Data
public class RequestDto {

    private String senderId;
    private String donorId;
    private String donorEmail;

    // Getters and Setters (or use Lombok @Data)
    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
    public String getdonorEmail() {
    	return donorEmail;
    }
    public void setdonorEmail(String donorEmail) {
    	this.donorEmail=donorEmail;
    }
    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }
}
