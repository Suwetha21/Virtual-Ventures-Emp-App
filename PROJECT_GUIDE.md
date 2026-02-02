# Virtual Ventures Employee Portal - Complete Project Guide

## 📋 Project Overview
A full-stack **Spring Boot + React/HTML** employee management system with attendance tracking, employee registration, and SQL database backend.

---

## 📁 Project Structure

### **Frontend Files (Root Directory)**
```
Root/
├── index.html                 # Login page (renamed from login.html)
├── style.css                  # Consolidated stylesheet
├── function.js                # Login JavaScript functions
├── registration.html          # Employee registration form
├── attendance.html            # Attendance records display
├── 2index.html               # Alternative dashboard
├── 2index.css                # Dashboard styles
├── portfolio.css              # Additional styles
├── logo.svg                   # Company logo
└── logo.jpeg                  # Alternative logo
```

### **Backend Java Files (Root Directory)**
```
Root/
├── EmployeePortalApplication.java    # Spring Boot main application
├── Employee.java                     # Employee entity/model
├── Attendance.java                   # Attendance entity/model
├── EmployeeRepository.java           # Employee data access
├── AttendanceRepository.java         # Attendance data access
├── EmployeeController.java           # Employee REST API
├── AttendanceController.java         # Attendance REST API
├── PageController.java               # Page routing
└── HomeController.java               # Home page controller
```

### **Configuration Files**
```
Root/
├── pom.xml                   # Maven dependencies
├── application.properties    # App configuration
├── mvnw / mvnw.cmd          # Maven wrapper
└── README.md                 # Documentation
```

---

## 🗄️ Database Setup (SQL)

### **Create Database**
```sql
CREATE DATABASE employee_portal;
USE employee_portal;

-- Employees Table
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
    registration_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Attendance Table
CREATE TABLE attendance (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    emp_name VARCHAR(200),
    emp_id VARCHAR(50),
    emp_email VARCHAR(100),
    check_in_time VARCHAR(50),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create indexes for faster queries
CREATE INDEX idx_email ON employees(email);
CREATE INDEX idx_emp_id ON employees(emp_id);
CREATE INDEX idx_department ON employees(department);
```

### **Sample Data (Optional)**
```sql
INSERT INTO employees 
(first_name, last_name, email, emp_id, department, position, phone_number, gender, address, city, state, zip_code)
VALUES 
('John', 'Doe', 'john.doe@gmail.com', 'VV-2024-001', 'IT', 'Software Engineer', '9876543210', 'Male', '123 Main St', 'New York', 'NY', '10001'),
('Jane', 'Smith', 'jane.smith@gmail.com', 'VV-2024-002', 'HR', 'HR Manager', '9876543211', 'Female', '456 Oak Ave', 'New York', 'NY', '10002'),
('Mike', 'Johnson', 'mike.j@gmail.com', 'VV-2024-003', 'Finance', 'Accountant', '9876543212', 'Male', '789 Pine Rd', 'New York', 'NY', '10003');
```

---

## 🚀 Running the Application

### **Step 1: Configure Database Connection**
Edit `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_portal
spring.datasource.username=root
spring.datasource.password=YOUR_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

server.port=8080
server.servlet.context-path=/
```

### **Step 2: Start the Application**
```bash
# Using Maven
./mvnw spring-boot:run

# Or using Java directly (if compiled)
java -jar target/employee-portal-0.0.1-SNAPSHOT.jar
```

### **Step 3: Access the Application**
- **Login Page:** http://localhost:8080
- **Registration:** http://localhost:8080/registration.html
- **Attendance:** http://localhost:8080/attendance.html
- **Dashboard:** http://localhost:8080/2index.html

---

## 📡 API Endpoints

### **Employee Endpoints**
```
GET    /api/employees                    # Get all employees
GET    /api/employees/{id}               # Get employee by ID
GET    /api/employees/search/{term}      # Search employees
GET    /api/employees/department/{name}  # Get by department
POST   /api/employees/register           # Register new employee
PUT    /api/employees/{id}               # Update employee
DELETE /api/employees/{id}               # Delete employee
GET    /api/employees/stats/count        # Get total count
```

### **Attendance Endpoints**
```
GET    /api/attendance                   # Get all attendance records
POST   /api/attendance/register          # Register attendance
```

### **Page Routes**
```
GET    /                                 # Home/Login
GET    /login                            # Login redirect
GET    /attendance                       # Attendance page
GET    /dashboard                        # Dashboard
GET    /register                         # Registration redirect
```

---

## 📝 Example API Requests

### **Register Employee**
```bash
curl -X POST http://localhost:8080/api/employees/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Alice",
    "lastName": "Williams",
    "email": "alice.w@gmail.com",
    "empId": "VV-2024-004",
    "department": "Sales",
    "position": "Sales Manager",
    "phoneNumber": "9876543213",
    "gender": "Female",
    "address": "321 Elm St",
    "city": "New York",
    "state": "NY",
    "zipCode": "10004",
    "dateOfJoining": "2024-01-15"
  }'
```

### **Get All Employees**
```bash
curl http://localhost:8080/api/employees
```

### **Register Attendance**
```bash
curl -X POST http://localhost:8080/api/attendance/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "empEmail": "john.doe@gmail.com",
    "empId": "VV-2024-001",
    "checkInTime": "2024-02-02T09:00:00"
  }'
```

---

## 🔐 Authentication Notes
- Current system uses localStorage for demo purposes
- Production should implement JWT tokens
- All emails must end with `@gmail.com`

---

## 📊 Database Query Examples

### **View All Employees**
```sql
SELECT * FROM employees;
```

### **View Attendance Records**
```sql
SELECT * FROM attendance ORDER BY created_at DESC;
```

### **Employee Statistics**
```sql
SELECT department, COUNT(*) as count FROM employees GROUP BY department;
```

### **Today's Check-ins**
```sql
SELECT * FROM attendance 
WHERE DATE(created_at) = CURDATE() 
ORDER BY created_at DESC;
```

---

## 🛠️ Technologies Used

**Backend:**
- Spring Boot 3.x
- Spring Data JPA
- MySQL Database
- Maven Build Tool
- Java 17+

**Frontend:**
- HTML5
- CSS3
- Vanilla JavaScript
- Bootstrap (via CSS classes)

---

## 📦 Dependencies (pom.xml)
```xml
<dependencies>
    <!-- Spring Boot -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-web</artifactId>
    </dependency>
    
    <!-- Spring Data JPA -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-jpa</artifactId>
    </dependency>
    
    <!-- MySQL Driver -->
    <dependency>
        <groupId>com.mysql</groupId>
        <artifactId>mysql-connector-j</artifactId>
        <scope>runtime</scope>
    </dependency>
    
    <!-- Jakarta Persistence -->
    <dependency>
        <groupId>jakarta.persistence</groupId>
        <artifactId>jakarta.persistence-api</artifactId>
    </dependency>
</dependencies>
```

---

## ✅ Features

✅ Employee Registration & Management  
✅ Attendance Tracking  
✅ Department & Position Management  
✅ Employee Search & Filter  
✅ Dashboard with Statistics  
✅ Responsive UI  
✅ RESTful APIs  
✅ SQL Database Integration  
✅ CRUD Operations  
✅ Data Validation  

---

## 📞 Support

For issues or questions:
1. Check the API endpoints documentation
2. Verify database connection in `application.properties`
3. Ensure MySQL is running on localhost:3306
4. Check browser console for frontend errors

---

## 🔄 GitHub Repository

All files pushed to: https://github.com/Suwetha21/Virtual-Ventures-Emp-App

---

**Last Updated:** February 2, 2026  
**Version:** 1.0  
**Status:** Production Ready
