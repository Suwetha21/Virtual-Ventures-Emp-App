-- Virtual Ventures Employee Portal - Database Initialization Script
-- Execute this script in MySQL to set up the complete database

-- Create Database
CREATE DATABASE IF NOT EXISTS employee_portal;
USE employee_portal;

-- Drop existing tables if they exist (careful in production!)
DROP TABLE IF EXISTS attendance;
DROP TABLE IF EXISTS employees;

-- ============================================
-- EMPLOYEES TABLE
-- ============================================
CREATE TABLE employees (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE,
    emp_id VARCHAR(50) UNIQUE,
    department VARCHAR(50) NOT NULL,
    position VARCHAR(100) NOT NULL,
    phone_number VARCHAR(20),
    date_of_joining VARCHAR(20),
    gender VARCHAR(20),
    address VARCHAR(200),
    city VARCHAR(50),
    state VARCHAR(50),
    zip_code VARCHAR(10),
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    INDEX idx_email (email),
    INDEX idx_emp_id (emp_id),
    INDEX idx_department (department)
);

-- ============================================
-- ATTENDANCE TABLE
-- ============================================
CREATE TABLE attendance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    emp_name VARCHAR(200),
    emp_id VARCHAR(50),
    emp_email VARCHAR(100),
    check_in_time VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    INDEX idx_emp_id (emp_id),
    INDEX idx_check_in_time (created_at)
);

-- ============================================
-- SAMPLE DATA (Optional - Remove for production)
-- ============================================

-- Insert Sample Employees
INSERT INTO employees 
(first_name, last_name, email, emp_id, department, position, phone_number, gender, address, city, state, zip_code, date_of_joining)
VALUES
('John', 'Doe', 'john.doe@gmail.com', 'VV-2024-001', 'IT', 'Software Engineer', '9876543210', 'Male', '123 Main Street', 'New York', 'NY', '10001', '2024-01-10'),
('Jane', 'Smith', 'jane.smith@gmail.com', 'VV-2024-002', 'HR', 'HR Manager', '9876543211', 'Female', '456 Oak Avenue', 'New York', 'NY', '10002', '2024-01-15'),
('Mike', 'Johnson', 'mike.johnson@gmail.com', 'VV-2024-003', 'Finance', 'Senior Accountant', '9876543212', 'Male', '789 Pine Road', 'New York', 'NY', '10003', '2024-01-20'),
('Sarah', 'Williams', 'sarah.williams@gmail.com', 'VV-2024-004', 'Sales', 'Sales Manager', '9876543213', 'Female', '321 Elm Street', 'New York', 'NY', '10004', '2024-01-25'),
('David', 'Brown', 'david.brown@gmail.com', 'VV-2024-005', 'IT', 'DevOps Engineer', '9876543214', 'Male', '654 Maple Lane', 'New York', 'NY', '10005', '2024-02-01');

-- Insert Sample Attendance Records
INSERT INTO attendance
(emp_name, emp_id, emp_email, check_in_time)
VALUES
('John Doe', 'VV-2024-001', 'john.doe@gmail.com', '2024-02-02 09:15:00'),
('Jane Smith', 'VV-2024-002', 'jane.smith@gmail.com', '2024-02-02 08:45:00'),
('Mike Johnson', 'VV-2024-003', 'mike.johnson@gmail.com', '2024-02-02 09:30:00'),
('John Doe', 'VV-2024-001', 'john.doe@gmail.com', '2024-02-01 09:10:00'),
('Sarah Williams', 'VV-2024-004', 'sarah.williams@gmail.com', '2024-02-01 09:00:00');

-- ============================================
-- VERIFY SETUP
-- ============================================
SELECT 'Employees Created' as Status, COUNT(*) as Total FROM employees;
SELECT 'Attendance Records Created' as Status, COUNT(*) as Total FROM attendance;

-- Display all employees
SELECT '--- ALL EMPLOYEES ---' as '';
SELECT id, first_name, last_name, email, emp_id, department, position FROM employees;

-- Display attendance records
SELECT '--- ATTENDANCE RECORDS ---' as '';
SELECT id, emp_name, emp_id, emp_email, check_in_time FROM attendance ORDER BY created_at DESC;

-- Department Summary
SELECT '--- DEPARTMENT SUMMARY ---' as '';
SELECT department, COUNT(*) as employee_count FROM employees GROUP BY department;
