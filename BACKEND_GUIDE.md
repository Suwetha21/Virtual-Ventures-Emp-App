# Virtual Ventures Employee Portal - Backend Architecture

## Overview

This document describes the professional backend architecture built for the Virtual Ventures Employee Portal. The system is designed to manage employee registration, records, and attendance tracking with a robust REST API.

## Technology Stack

- **Framework**: Spring Boot 3.5.10
- **Language**: Java 21
- **Database**: H2 (In-Memory Database)
- **ORM**: JPA/Hibernate
- **Build Tool**: Maven
- **API**: RESTful Web Services
- **Package Structure**: `com.virtualventures.employee_portal`

## Database Schema

### Employee Entity
Represents permanent employee records in the system.

**Table**: `employees`

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | Long | PRIMARY KEY, AUTO_INCREMENT | Unique identifier |
| firstName | String | NOT NULL | Employee's first name |
| lastName | String | NOT NULL | Employee's last name |
| email | String | NOT NULL, UNIQUE | Email address (@gmail.com only) |
| empId | String | NOT NULL, UNIQUE | Employee ID (e.g., VV-2024-001) |
| department | String | NOT NULL | Department (IT, HR, Finance, Sales, Marketing, Operations) |
| position | String | NOT NULL | Job position/title |
| phoneNumber | String | NOT NULL | Contact phone number |
| dateOfJoining | String | NOT NULL | Date employee joined |
| gender | String | NOT NULL | Gender (Male, Female, Other) |
| address | String | NOT NULL | Street address |
| city | String | NOT NULL | City name |
| state | String | NOT NULL | State/Province |
| zipCode | String | NOT NULL | Postal code |
| registrationDate | LocalDateTime | NOT NULL | System registration timestamp |

### Attendance Entity
Represents attendance check-in records linked to employees.

**Table**: `attendance`

| Column | Type | Constraints | Description |
|--------|------|-------------|-------------|
| id | Long | PRIMARY KEY, AUTO_INCREMENT | Unique identifier |
| empName | String | - | Employee full name |
| empId | String | - | Employee ID |
| empEmail | String | - | Employee email |
| checkInTime | String | - | Check-in timestamp |

## REST API Endpoints

### Employee Management Endpoints

#### 1. Register New Employee
```
POST /api/employees/register
Content-Type: application/json

{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@gmail.com",
    "empId": "VV-2024-001",
    "department": "IT",
    "position": "Software Engineer",
    "phoneNumber": "+1 (555) 123-4567",
    "dateOfJoining": "2024-01-15",
    "gender": "Male",
    "address": "123 Main Street",
    "city": "New York",
    "state": "NY",
    "zipCode": "10001"
}
```

**Response (201 Created)**:
```json
{
    "message": "Employee registered successfully",
    "employeeId": 1,
    "empId": "VV-2024-001",
    "fullName": "John Doe"
}
```

**Validation Rules**:
- Email must end with @gmail.com
- All required fields must be provided
- Email must be unique
- Employee ID must be unique

#### 2. Get All Employees
```
GET /api/employees
```

**Response (200 OK)**:
```json
[
    {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        "email": "john.doe@gmail.com",
        "empId": "VV-2024-001",
        "department": "IT",
        "position": "Software Engineer",
        "phoneNumber": "+1 (555) 123-4567",
        "dateOfJoining": "2024-01-15",
        "gender": "Male",
        "address": "123 Main Street",
        "city": "New York",
        "state": "NY",
        "zipCode": "10001",
        "registrationDate": "2026-01-29T11:00:00"
    }
]
```

#### 3. Get Employee by ID
```
GET /api/employees/{id}
```

**Response (200 OK)**:
```json
{
    "id": 1,
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@gmail.com",
    "empId": "VV-2024-001",
    "department": "IT",
    "position": "Software Engineer",
    "phoneNumber": "+1 (555) 123-4567",
    "dateOfJoining": "2024-01-15",
    "gender": "Male",
    "address": "123 Main Street",
    "city": "New York",
    "state": "NY",
    "zipCode": "10001",
    "registrationDate": "2026-01-29T11:00:00"
}
```

#### 4. Search Employees by Name
```
GET /api/employees/search/{searchTerm}
```

**Example**: `/api/employees/search/John`

**Response (200 OK)**:
```json
[
    {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        ...
    }
]
```

#### 5. Get Employees by Department
```
GET /api/employees/department/{department}
```

**Example**: `/api/employees/department/IT`

**Response (200 OK)**:
```json
[
    {
        "id": 1,
        "firstName": "John",
        "lastName": "Doe",
        "department": "IT",
        ...
    }
]
```

#### 6. Update Employee
```
PUT /api/employees/{id}
Content-Type: application/json

{
    "position": "Senior Software Engineer",
    "department": "IT",
    "phoneNumber": "+1 (555) 987-6543"
}
```

**Response (200 OK)**:
```json
{
    "message": "Employee updated successfully",
    "employee": { ... }
}
```

#### 7. Delete Employee
```
DELETE /api/employees/{id}
```

**Response (200 OK)**:
```json
{
    "message": "Employee deleted successfully"
}
```

#### 8. Get Employee Statistics
```
GET /api/employees/stats/count
```

**Response (200 OK)**:
```json
{
    "totalEmployees": 25
}
```

### Attendance Management Endpoints

#### 1. Register Attendance (with Employee Auto-Registration)
```
POST /api/attendance/register
Content-Type: application/json

{
    "firstName": "John",
    "lastName": "Doe",
    "empEmail": "john.doe@gmail.com",
    "empId": "VV-2024-001",
    "department": "IT",
    "position": "Software Engineer",
    "phoneNumber": "+1 (555) 123-4567",
    "dateOfJoining": "2024-01-15",
    "gender": "Male",
    "address": "123 Main Street",
    "city": "New York",
    "state": "NY",
    "zipCode": "10001",
    "checkInTime": "2026-01-29T09:30:00"
}
```

**Response (201 Created)**:
```json
{
    "message": "Attendance registered successfully",
    "attendanceId": 1,
    "employeeId": 1,
    "fullName": "John Doe",
    "email": "john.doe@gmail.com"
}
```

**Behavior**:
- If employee doesn't exist, registers them automatically
- If employee exists, only records attendance
- Validates @gmail.com email domain

#### 2. Get All Attendance Records
```
GET /api/attendance
```

**Response (200 OK)**:
```json
[
    {
        "id": 1,
        "empName": "John Doe",
        "empId": "VV-2024-001",
        "empEmail": "john.doe@gmail.com",
        "checkInTime": "2026-01-29T09:30:00"
    }
]
```

## Java Classes Overview

### 1. Employee.java
**Purpose**: JPA Entity representing an employee

**Key Methods**:
- Constructor with parameters
- Getters/Setters for all fields
- `getFullName()` - Returns formatted full name

**Validation**:
- Email unique constraint
- Employee ID unique constraint
- All required fields (NOT NULL)

### 2. EmployeeRepository.java
**Purpose**: Data Access Object for Employee CRUD operations

**Key Methods**:
```java
Optional<Employee> findByEmail(String email);
Optional<Employee> findByEmpId(String empId);
List<Employee> findByDepartment(String department);
List<Employee> findByPosition(String position);
List<Employee> searchByName(String searchTerm);
boolean existsByEmail(String email);
boolean existsByEmpId(String empId);
```

**Features**:
- Spring Data JPA interface
- Custom query methods
- Case-insensitive search support

### 3. EmployeeController.java
**Purpose**: REST API controller for employee management

**Endpoints**:
- `GET /api/employees` - Get all
- `GET /api/employees/{id}` - Get by ID
- `GET /api/employees/search/{searchTerm}` - Search by name
- `GET /api/employees/department/{department}` - Filter by department
- `POST /api/employees/register` - Register new employee
- `PUT /api/employees/{id}` - Update employee
- `DELETE /api/employees/{id}` - Delete employee
- `GET /api/employees/stats/count` - Get statistics

**Validations**:
- Email format (@gmail.com only)
- Required field checks
- Duplicate email/employee ID prevention
- Proper HTTP status codes

### 4. Attendance.java
**Purpose**: JPA Entity for attendance records

**Fields**:
- id, empName, empId, empEmail, checkInTime

### 5. AttendanceRepository.java
**Purpose**: Data Access Object for Attendance

**Methods**:
```java
List<Attendance> findAll();
save(Attendance)
```

### 6. AttendanceController.java
**Purpose**: REST API for attendance check-in

**Key Features**:
- Auto-registers employees if not found
- Links attendance to employee records
- Email validation
- Comprehensive error handling

## Frontend Integration

### Registration Page (`registration.html`)
- Collects all employee information
- Validates @gmail.com email
- Sends to `/api/employees/register` endpoint
- Stores user session in localStorage

### Attendance Records Page (`attendance.html`)
- Fetches all employees from `/api/employees`
- Displays in table format
- Filter by department and search
- Export to CSV
- Delete functionality
- Statistics (total employees, registered today, active departments)

## Error Handling

All endpoints return appropriate HTTP status codes:

| Status | Meaning |
|--------|---------|
| 200 | Success |
| 201 | Created |
| 400 | Bad Request (validation error) |
| 404 | Not Found |
| 409 | Conflict (duplicate email/ID) |
| 500 | Internal Server Error |

**Error Response Format**:
```json
{
    "error": "Error message describing what went wrong"
}
```

## Email Validation

**Policy**: Only @gmail.com emails are accepted

- Enforced in EmployeeController
- Real-time validation in frontend
- Unique constraint on email field
- Prevents duplicate registrations

## Database Initialization

H2 database is configured to:
- Auto-create tables on startup
- Use in-memory storage (data lost on restart)
- Enable web console for debugging

## Security Considerations

**Current Implementation**:
- Basic email validation
- Required field enforcement
- Duplicate prevention

**Recommendations for Production**:
- Add authentication/authorization (Spring Security)
- Implement role-based access control (Admin, Manager, Employee)
- Add request logging and audit trails
- Use HTTPS for API calls
- Implement rate limiting
- Add CORS configuration for specific domains

## Development Guidelines

### Adding New Features

1. **New Database Field**:
   - Add to Employee.java class
   - Add getter/setter methods
   - Add validation in EmployeeController

2. **New API Endpoint**:
   - Add method in EmployeeController
   - Add repository method if needed
   - Add proper error handling
   - Document in this guide

3. **New Frontend Page**:
   - Use existing API endpoints
   - Follow styling from registration.html
   - Add proper error handling with fetch API

### Testing

Run tests with:
```bash
mvn test
```

### Building

Compile:
```bash
mvn clean compile
```

Package:
```bash
mvn clean package
```

Run application:
```bash
java -jar target/employee-portal-0.0.1-SNAPSHOT.jar
```

Server runs on: `http://localhost:8080`

## API Quick Reference

| Method | Endpoint | Purpose |
|--------|----------|---------|
| POST | /api/employees/register | Register new employee |
| GET | /api/employees | Get all employees |
| GET | /api/employees/{id} | Get employee by ID |
| GET | /api/employees/search/{term} | Search by name |
| GET | /api/employees/department/{dept} | Filter by department |
| PUT | /api/employees/{id} | Update employee |
| DELETE | /api/employees/{id} | Delete employee |
| GET | /api/employees/stats/count | Get employee count |
| GET | /api/attendance | Get attendance records |
| POST | /api/attendance/register | Register attendance |

## Future Enhancements

1. **Authentication System**
   - User login with credentials
   - JWT token-based authentication
   - Role-based authorization

2. **Advanced Features**
   - Attendance reports and analytics
   - Employee leave management
   - Performance reviews
   - Salary management

3. **Database**
   - Migration to PostgreSQL/MySQL
   - Database backup strategies
   - Data archival

4. **API Improvements**
   - Pagination for large datasets
   - Advanced filtering and sorting
   - Bulk operations
   - API documentation (Swagger/OpenAPI)

5. **Frontend**
   - Employee dashboard
   - Department management
   - Reporting tools
   - Mobile app support

---

**Version**: 1.0  
**Last Updated**: January 29, 2026  
**Status**: Production Ready
