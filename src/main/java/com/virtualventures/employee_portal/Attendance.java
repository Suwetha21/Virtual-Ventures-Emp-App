package com.virtualventures.employee_portal;

import jakarta.persistence.*;

// Virtual Ventures Attendance Entity
@Entity
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String empName;
    private String empId;
    private String empEmail;
    private String checkInTime;

    // --- GETTERS AND SETTERS (Add these so Java can access the data) ---

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getEmpName() { return empName; }
    public void setEmpName(String empName) { this.empName = empName; }

    public String getEmpId() { return empId; }
    public void setEmpId(String empId) { this.empId = empId; }

    public String getEmpEmail() { return empEmail; }
    public void setEmpEmail(String empEmail) { this.empEmail = empEmail; }

    public String getCheckInTime() { return checkInTime; }
    public void setCheckInTime(String checkInTime) { this.checkInTime = checkInTime; }
}
