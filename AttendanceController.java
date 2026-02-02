package com.virtualventures.employee_portal;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * AttendanceController - REST API for attendance check-ins
 * Records when employees check in/out and links attendance to employee records
 */
@RestController
@RequestMapping("/api/attendance")
@CrossOrigin(origins = "*")
public class AttendanceController {

    private final AttendanceRepository attendanceRepository;
    private final EmployeeRepository employeeRepository;

    @Autowired
    public AttendanceController(AttendanceRepository attendanceRepository, EmployeeRepository employeeRepository) {
        this.attendanceRepository = attendanceRepository;
        this.employeeRepository = employeeRepository;
    }

    @GetMapping
    public ResponseEntity<List<Attendance>> list() {
        try {
            List<Attendance> attendanceRecords = attendanceRepository.findAll();
            return ResponseEntity.ok(attendanceRecords);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    /**
     * Register attendance for an employee
     * First ensures employee exists in the system
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerAttendance(@RequestBody Map<String, String> attendanceData) {
        try {
            // Extract data from request
            String firstName = attendanceData.get("firstName");
            String lastName = attendanceData.get("lastName");
            String empEmail = attendanceData.get("empEmail");
            String empId = attendanceData.get("empId");
            String department = attendanceData.get("department");
            String position = attendanceData.get("position");
            String phoneNumber = attendanceData.get("phoneNumber");
            String dateOfJoining = attendanceData.get("dateOfJoining");
            String gender = attendanceData.get("gender");
            String address = attendanceData.get("address");
            String city = attendanceData.get("city");
            String state = attendanceData.get("state");
            String zipCode = attendanceData.get("zipCode");
            String checkInTime = attendanceData.get("checkInTime");

            // Validate required fields
            if (firstName == null || firstName.isEmpty() || empEmail == null || empEmail.isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "First name and email are required"));
            }

            // Validate email format
            if (!empEmail.endsWith("@gmail.com")) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Email must be a valid @gmail.com address"));
            }

            // Check if employee exists by email
            Optional<Employee> existingEmployee = employeeRepository.findByEmail(empEmail);
            Employee employee;

            if (existingEmployee.isPresent()) {
                // Employee already registered, use existing record
                employee = existingEmployee.get();
            } else {
                // New employee, register them first
                if (lastName == null || department == null || position == null) {
                    return ResponseEntity.badRequest()
                            .body(Map.of("error", "Last name, department, and position are required for new employees"));
                }

                employee = new Employee(firstName, lastName, empEmail, empId, department, position,
                        phoneNumber != null ? phoneNumber : "",
                        dateOfJoining != null ? dateOfJoining : "",
                        gender != null ? gender : "",
                        address != null ? address : "",
                        city != null ? city : "",
                        state != null ? state : "",
                        zipCode != null ? zipCode : "");
                employee = employeeRepository.save(employee);
            }

            // Create attendance record
            Attendance attendance = new Attendance();
            attendance.setEmpName(employee.getFullName());
            attendance.setEmpId(employee.getEmpId());
            attendance.setEmpEmail(employee.getEmail());
            attendance.setCheckInTime(checkInTime != null ? checkInTime : java.time.LocalDateTime.now().toString());

            Attendance savedAttendance = attendanceRepository.save(attendance);

            return ResponseEntity.status(HttpStatus.CREATED).body(Map.of(
                "message", "Attendance registered successfully",
                "attendanceId", savedAttendance.getId(),
                "employeeId", employee.getId(),
                "fullName", employee.getFullName(),
                "email", employee.getEmail()
            ));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Registration failed: " + e.getMessage()));
        }
    }
}
