package com.example.demo.entity;

import jakarta.persistence.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;

@Entity
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    @Column(name = "gender", nullable = false)
    private String gender;

    @Column(name = "dob", nullable = false)
    private LocalDate dob;

    @Column(name = "age", nullable = false)
    private Integer age;

    @Column(name = "blood_group", nullable = false, length = 10)
    private String bloodGroup;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "state", nullable = false, length = 100)
    private String state;

    @Column(name = "city", nullable = false, length = 100)
    private String city;

    @Column(name = "phone", nullable = false, length = 15)
    private String phone;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "password_hash", nullable = false, length = 255)
    private String passwordHash;

    @Column(name = "eligible", nullable = false)
    private String eligibility; 

    @Column(name = "cpt", nullable = false)
    private Integer cpt = 0;

    @Column(name = "lastdonationdate")
    private LocalDate donationDate;
   
    @Column(name = "data_hash", nullable = false, length = 255)
    private String dataHash;

   
   
    // 🔹 Getters & Setters
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public String getLastName() { return lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public LocalDate getDob() { return dob; }
    public void setDob(LocalDate dob) { this.dob = dob; }

    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }

    public String getBloodGroup() { return bloodGroup; }
    public void setBloodGroup(String bloodGroup) { this.bloodGroup = bloodGroup; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getState() { return state; }
    public void setState(String state) { this.state = state; }

    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getEligibility() { return eligibility; }
    public void setEligibility(String eligibility) { this.eligibility = eligibility; }

    public Integer getCpt() { return cpt; }
    public void setCpt(Integer cpt) { this.cpt = cpt; }
    
    public LocalDate getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(LocalDate donationDate) {
        this.donationDate = donationDate;
    }

    public String getDataHash() { return dataHash; }
    public void setDataHash(String dataHash) { this.dataHash = dataHash; }

    // 🔹 Generate Hash (SHA-256) for Critical Fields
   
 // ✅ Generate Hash (SHA-256) for Critical Fields
    public void generateDataHash() {
        String rawData = firstName + lastName + gender + dob + age + bloodGroup + 
                         address + state + city + phone + email + passwordHash;
        this.dataHash = hashSHA256(rawData);
    }
    @PrePersist
    private void generateDataHashOnPersist() {
        generateDataHash();
    }


    // 🔹 Hashing Function
    private String hashSHA256(String data) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(data.getBytes(StandardCharsets.UTF_8));

            // Convert byte array to hex string
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