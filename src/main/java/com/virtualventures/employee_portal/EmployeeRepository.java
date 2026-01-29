package com.virtualventures.employee_portal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

/**
 * EmployeeRepository - Data Access Object for Employee entity
 * Provides database operations for employees
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    /**
     * Find employee by email (emails are unique)
     */
    Optional<Employee> findByEmail(String email);
    
    /**
     * Find employee by employee ID (emp IDs are unique)
     */
    Optional<Employee> findByEmpId(String empId);
    
    /**
     * Find all employees in a specific department
     */
    List<Employee> findByDepartment(String department);
    
    /**
     * Find all employees with a specific position
     */
    List<Employee> findByPosition(String position);
    
    /**
     * Search employees by first or last name (case-insensitive)
     */
    @Query("SELECT e FROM Employee e WHERE " +
           "LOWER(e.firstName) LIKE LOWER(CONCAT('%', :searchTerm, '%')) OR " +
           "LOWER(e.lastName) LIKE LOWER(CONCAT('%', :searchTerm, '%'))")
    List<Employee> searchByName(@Param("searchTerm") String searchTerm);
    
    /**
     * Count total employees
     */
    long count();
    
    /**
     * Check if email already exists
     */
    boolean existsByEmail(String email);
    
    /**
     * Check if employee ID already exists
     */
    boolean existsByEmpId(String empId);
}
