package com.example.demo.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "REQUEST")
public class Request {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "DONORID", nullable = false)
    private String donorId;

    @Column(name = "SENDERID", nullable = false)
    private String senderId;

    @Column(name = "DONOREMAIL", nullable = false)
    private String donorEmail;

    @Column(name = "NAME", nullable = false)
    private String donorName;

    @Column(name = "PHONE", nullable = false)
    private String donorPhone;

    @Column(name = "BLOODGROUP", nullable = false)
    private String donorBloodgroup;

    @Column(name = "STATUS")
    private String status = "PENDING";
    @Column(name = "MESSAGE")
    private String message;
    
   @Column(name = "CREATED_AT")
   private LocalDateTime createdAt;
   @Column(name = "ADDRESS")
   private String address;
    // Getters and Setters
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public String getDonorEmail() {
        return donorEmail;
    }

    public void setDonorEmail(String donorEmail) {
        this.donorEmail = donorEmail;
    }

    public String getDonorName() {
        return donorName;
    }

    public void setDonorName(String donorName) {
        this.donorName = donorName;
    }

    public String getDonorPhone() {
        return donorPhone;
    }

    public void setDonorPhone(String donorPhone) {
        this.donorPhone = donorPhone;
    }

    public String getDonorBloodgroup() {
        return donorBloodgroup;
    }

    public void setDonorBloodgroup(String donorBloodgroup) {
        this.donorBloodgroup = donorBloodgroup;
    }
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public String getStatus() {
	    return status;
	}
	
	public void setStatus(String status) {
	    this.status = status;
	}
	public LocalDateTime getcreatedAt() {
	    return createdAt;
	}
	
	public void setcreatedAt(LocalDateTime createdAt) {
	    this.createdAt = createdAt;
	}
	public String getAddress() {
	    return address;
	}
	
	public void setAddress(String address) {
	    this.address = address;
	}
}