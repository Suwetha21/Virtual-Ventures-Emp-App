package com.virtualventures.employee_portal;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Employee Entity - Represents an employee in the Virtual Ventures system
 * Stores permanent employee information
 */
@Entity
@Table(name = "employees")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false)
    private String firstName;
    
    @Column(nullable = false)
    private String lastName;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @Column(nullable = false, unique = true)
    private String empId;
    
    @Column(nullable = false)
    private String department;
    
    @Column(nullable = false)
    private String position;
    
    @Column(nullable = false)
    private String phoneNumber;
    
    @Column(nullable = false)
    private String dateOfJoining;
    
    @Column(nullable = false)
    private String gender;
    
    @Column(nullable = false)
    private String address;
    
    @Column(nullable = false)
    private String city;
    
    @Column(nullable = false)
    private String state;
    
    @Column(nullable = false)
    private String zipCode;
    
    @Column(nullable = false)
    private LocalDateTime registrationDate;
    
    // Constructors
    public Employee() {
        this.registrationDate = LocalDateTime.now();
    }
    
    public Employee(String firstName, String lastName, String email, String empId, 
                   String department, String position, String phoneNumber, String dateOfJoining,
                   String gender, String address, String city, String state, String zipCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.empId = empId;
        this.department = department;
        this.position = position;
        this.phoneNumber = phoneNumber;
        this.dateOfJoining = dateOfJoining;
        this.gender = gender;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.registrationDate = LocalDateTime.now();
    }
    
    // Getters and Setters
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getFirstName() {
        return firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    public String getLastName() {
        return lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getEmpId() {
        return empId;
    }
    
    public void setEmpId(String empId) {
        this.empId = empId;
    }
    
    public String getDepartment() {
        return department;
    }
    
    public void setDepartment(String department) {
        this.department = department;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    public String getPhoneNumber() {
        return phoneNumber;
    }
    
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String getDateOfJoining() {
        return dateOfJoining;
    }
    
    public void setDateOfJoining(String dateOfJoining) {
        this.dateOfJoining = dateOfJoining;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }
    
    public String getState() {
        return state;
    }
    
    public void setState(String state) {
        this.state = state;
    }
    
    public String getZipCode() {
        return zipCode;
    }
    
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
    
    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }
    
    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    /**
     * Get full name for display
     */
    public String getFullName() {
        return firstName + " " + lastName;
    }
}
