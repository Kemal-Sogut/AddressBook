package org.example;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class BuddyInfo {
    @Id
    @GeneratedValue
    private Long id;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    private String name;
    private String address;
    private String phoneNumber;
    private String email;

    // Default constructor with NO data.
    public BuddyInfo() {
        this.name = "Unknown";
        this.address = "Unknown";
        this.phoneNumber = "Unknown";
        this.email = "Unknown";
    }

    // Constructor with data
    public BuddyInfo(String name, String address, String phoneNumber, String email) {
        this.name = name;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    // Getters and Setters
    public String getName() {return name;}

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return name + ", " + address + ", " + phoneNumber;
    }
}
