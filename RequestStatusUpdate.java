package com.example.demo.models;

public class RequestStatusUpdate {
    private String donorId;
    private String status;

    // Default constructor (needed for serialization)
    public RequestStatusUpdate() {}

    // Parameterized constructor
    public RequestStatusUpdate(String donorId, String status) {
        this.donorId = donorId;
        this.status = status;
    }

    // Getter for donorId
    public String getDonorId() {
        return donorId;
    }

    // Setter for donorId
    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    // Getter for status
    public String getStatus() {
        return status;
    }

    // Setter for status
    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "RequestStatusUpdate{" +
                "donorId='" + donorId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}