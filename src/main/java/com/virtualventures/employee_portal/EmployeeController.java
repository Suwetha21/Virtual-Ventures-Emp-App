package com.virtualventures.employee_portal;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * EmployeeController - REST API for employee management
 * Handles employee registration, retrieval, and updates
 */
@RestController
@RequestMapping("/api/employees")
@CrossOrigin(origins = "*")
public class EmployeeController {
    
    private final EmployeeRepository employeeRepository;
    
    @Autowired
    public EmployeeController(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    
    /**
     * GET: Retrieve all employees
     */
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees() {
        try {
            List<Employee> employees = employeeRepository.findAll();
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * GET: Retrieve employee by ID
     */
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Invalid employee ID"));
            }
            Optional<Employee> employee = employeeRepository.findById(id);
            if (employee.isPresent()) {
                return ResponseEntity.ok(employee.get());
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(Map.of("error", "Employee not found"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Server error"));
        }
    }
    
    /**
     * GET: Search employees by name
     */
    @GetMapping("/search/{searchTerm}")
    public ResponseEntity<List<Employee>> searchEmployees(@PathVariable String searchTerm) {
        try {
            List<Employee> employees = employeeRepository.searchByName(searchTerm);
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * GET: Get employees by department
     */
    @GetMapping("/department/{department}")
    public ResponseEntity<List<Employee>> getByDepartment(@PathVariable String department) {
        try {
            List<Employee> employees = employeeRepository.findByDepartment(department);
            return ResponseEntity.ok(employees);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    /**
     * POST: Register new employee
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerEmployee(@RequestBody Employee employee) {
        try {
            // Validation
            if (employee == null || employee.getEmail() == null || employee.getEmail().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Email is required"));
            }
            
            // Validate email format (must be @gmail.com)
            if (!employee.getEmail().endsWith("@gmail.com")) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Email must be a valid @gmail.com address"));
            }
            
            // Check if email already exists
            if (employeeRepository.existsByEmail(employee.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("error", "Email already registered"));
            }
            
            // Check if employee ID already exists
            if (employee.getEmpId() != null && employeeRepository.existsByEmpId(employee.getEmpId())) {
                return ResponseEntity.status(HttpStatus.CONFLICT)
                        .body(Map.of("error", "Employee ID already exists"));
            }
            
            // Validate required fields
            if (employee.getFirstName() == null || employee.getFirstName().isEmpty() ||
                employee.getLastName() == null || employee.getLastName().isEmpty() ||
                employee.getDepartment() == null || employee.getDepartment().isEmpty() ||
                employee.getPosition() == null || employee.getPosition().isEmpty()) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "All required fields must be filled"));
            }
            
            // Save employee
            Employee savedEmployee = employeeRepository.save(employee);
            
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(Map.of(
                        "message", "Employee registered successfully",
                        "employeeId", savedEmployee.getId(),
                        "empId", savedEmployee.getEmpId(),
                        "email", savedEmployee.getEmail(),
                        "fullName", savedEmployee.getFullName()
                    ));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Registration failed: " + e.getMessage()));
        }
    }
    
    /**
     * PUT: Update employee information
     */
    @PutMapping("/{id}")
    public ResponseEntity<?> updateEmployee(@PathVariable Long id, @RequestBody Employee employeeDetails) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Invalid employee ID"));
            }
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);
            if (!optionalEmployee.isPresent()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Employee not found"));
            }
            
            Employee employee = optionalEmployee.get();
            
            // Update fields if provided
            if (employeeDetails.getFirstName() != null) employee.setFirstName(employeeDetails.getFirstName());
            if (employeeDetails.getLastName() != null) employee.setLastName(employeeDetails.getLastName());
            if (employeeDetails.getDepartment() != null) employee.setDepartment(employeeDetails.getDepartment());
            if (employeeDetails.getPosition() != null) employee.setPosition(employeeDetails.getPosition());
            if (employeeDetails.getPhoneNumber() != null) employee.setPhoneNumber(employeeDetails.getPhoneNumber());
            if (employeeDetails.getAddress() != null) employee.setAddress(employeeDetails.getAddress());
            if (employeeDetails.getCity() != null) employee.setCity(employeeDetails.getCity());
            if (employeeDetails.getState() != null) employee.setState(employeeDetails.getState());
            if (employeeDetails.getZipCode() != null) employee.setZipCode(employeeDetails.getZipCode());
            
            Employee updatedEmployee = employeeRepository.save(employee);
            
            return ResponseEntity.ok(Map.of(
                "message", "Employee updated successfully",
                "employee", updatedEmployee
            ));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Update failed: " + e.getMessage()));
        }
    }
    
    /**
     * DELETE: Remove employee
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        try {
            if (id == null || id <= 0) {
                return ResponseEntity.badRequest()
                        .body(Map.of("error", "Invalid employee ID"));
            }
            
            if (!employeeRepository.existsById(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Employee not found"));
            }
            
            employeeRepository.deleteById(id);
            
            return ResponseEntity.ok(Map.of("message", "Employee deleted successfully"));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", "Deletion failed: " + e.getMessage()));
        }
    }
    
    /**
     * GET: Dashboard statistics
     */
    @GetMapping("/stats/count")
    public ResponseEntity<?> getEmployeeCount() {
        try {
            long count = employeeRepository.count();
            return ResponseEntity.ok(Map.of("totalEmployees", count));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
