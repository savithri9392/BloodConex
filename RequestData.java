package com.example.demo.models;

 // Adjust this import based on your project package structure

public class RequestData {

    private Long requestId;
    private String senderId;
    private Long donorId;
    private String donorEmail;
    private String status;
    private String message;

    // Default constructor
    public RequestData() {}

    // Constructor with parameters
    public RequestData(Long requestId, String senderId, Long donorId, String donorEmail, String status, String message) {
        this.requestId = requestId;
        this.senderId = senderId;
        this.donorId = donorId;
        this.donorEmail = donorEmail;
        this.status = status;
        this.message = message;
    }

    // Getters and setters
    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    public Long getDonorId() {
        return donorId;
    }

    public void setDonorId(Long donorId) {
        this.donorId = donorId;
    }

    public String getDonorEmail() {
        return donorEmail;
    }

    public void setDonorEmail(String donorEmail) {
        this.donorEmail = donorEmail;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "RequestData{" +
                "requestId=" + requestId +
                ", senderId='" + senderId + '\'' +
                ", donorId=" + donorId +
                ", donorEmail='" + donorEmail + '\'' +
                ", status='" + status + '\'' +
                ", message='" + message + '\'' +
                '}';
    }

	public Object getRecipientName() {
		// TODO Auto-generated method stub
		return null;
	}
}