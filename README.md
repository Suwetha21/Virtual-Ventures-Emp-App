# 🚀 Virtual Ventures Employee Portal - Complete Setup Guide

## ✅ System Status: PRODUCTION READY

A full-stack **Spring Boot + MySQL + HTML/CSS/JS** employee management system with attendance tracking and dashboard.

---

## 📦 Project Structure

All files are in the **root directory** for easy access and GitHub Pages deployment:

### Frontend Files
- `index.html` - Login page
- `registration.html` - Employee registration
- `attendance.html` - Attendance records
- `2index.html` - Dashboard
- `style.css` - Main stylesheet
- `function.js` - JavaScript functions
- `logo.svg` - Company logo

### Backend Java Files  
- `EmployeePortalApplication.java` - Spring Boot main app
- `Employee.java` - Employee model
- `Attendance.java` - Attendance model
- `EmployeeController.java` - REST API endpoints
- `AttendanceController.java` - Attendance API
- `EmployeeRepository.java` - Database access
- `PageController.java` - Page routing

### Configuration & Documentation
- `pom.xml` - Maven dependencies
- `application.properties` - App config
- `database_init.sql` - SQL initialization script
- `PROJECT_GUIDE.md` - Detailed documentation
- `start.bat` / `start.sh` - Quick start scripts

---

## 🛠️ Prerequisites

Before running the application, ensure you have:

✅ **Java 17+** installed  
✅ **Maven 3.6+** installed  
✅ **MySQL 8.0+** running on localhost:3306  
✅ MySQL user with password credentials  

---

## 🚀 Quick Start (3 Steps)

### Step 1: Initialize Database

```bash
# Open MySQL command line or MySQL Workbench
mysql -u root -p

# Execute the initialization script
source database_init.sql;

# Or import the file from MySQL Workbench
```

### Step 2: Configure Connection

Edit `application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/employee_portal
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.jpa.hibernate.ddl-auto=update
```

### Step 3: Run Application

**Windows:**
```bash
start.bat
```

**Linux/Mac:**
```bash
chmod +x start.sh
./start.sh
```

**Or Manual:**
```bash
mvn clean package -DskipTests
java -jar target/employee-portal-*.jar
```

---

## 🌐 Access the Application

Once running, open your browser:

| Feature | URL |
|---------|-----|
| 🔐 Login | http://localhost:8080 |
| 📝 Registration | http://localhost:8080/registration.html |
| 📋 Attendance | http://localhost:8080/attendance.html |
| 📊 Dashboard | http://localhost:8080/2index.html |

---

## 📡 REST API Endpoints

### Employee Management
```
GET    /api/employees                      # Get all employees
GET    /api/employees/{id}                 # Get by ID
GET    /api/employees/search/{term}        # Search by name
GET    /api/employees/department/{name}    # Filter by department
POST   /api/employees/register             # Register new employee
PUT    /api/employees/{id}                 # Update employee
DELETE /api/employees/{id}                 # Delete employee
```

### Attendance
```
GET    /api/attendance                     # Get all records
POST   /api/attendance/register            # Register check-in
```

### Page Routes
```
GET    /                                   # Home/Login page
GET    /login                              # Login redirect
GET    /attendance                         # Attendance page redirect
GET    /dashboard                          # Dashboard redirect
```

---

## 💾 Database Tables

### Employees Table
```sql
CREATE TABLE employees (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    emp_id VARCHAR(50) UNIQUE,
    department VARCHAR(50),
    position VARCHAR(100),
    phone_number VARCHAR(20),
    date_of_joining VARCHAR(20),
    gender VARCHAR(20),
    address VARCHAR(200),
    city VARCHAR(50),
    state VARCHAR(50),
    zip_code VARCHAR(10),
    registration_date TIMESTAMP
);
```

### Attendance Table
```sql
CREATE TABLE attendance (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    emp_name VARCHAR(200),
    emp_id VARCHAR(50),
    emp_email VARCHAR(100),
    check_in_time VARCHAR(50),
    created_at TIMESTAMP
);
```

---

## 📝 Example API Usage

### Register Employee
```bash
curl -X POST http://localhost:8080/api/employees/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "Alice",
    "lastName": "Williams",
    "email": "alice@gmail.com",
    "empId": "VV-2024-001",
    "department": "IT",
    "position": "Developer",
    "phoneNumber": "1234567890",
    "dateOfJoining": "2024-02-01",
    "gender": "Female",
    "address": "123 Main St",
    "city": "New York",
    "state": "NY",
    "zipCode": "10001"
  }'
```

### Get All Employees
```bash
curl http://localhost:8080/api/employees
```

### View Attendance Records
```bash
curl http://localhost:8080/api/attendance
```

---

## 🎯 Features

✅ Employee Registration & Management  
✅ Attendance Tracking with Check-in  
✅ Department & Position Management  
✅ Advanced Search & Filtering  
✅ Dashboard with Statistics  
✅ Responsive Design  
✅ RESTful APIs  
✅ MySQL Database  
✅ Data Validation  
✅ CRUD Operations  

---

## 🎯 How to Use the Portal

### Option 1: Register via Web Form

1. Go to: `http://localhost:8080/registration.html`
2. Fill in all required fields
3. Email must end with `@gmail.com`
4. Click **"Register"**
5. Employee will appear in records immediately

### Option 2: Register via API

Use the curl command above or any API client (Postman, etc.)

### Option 3: Add Sample Data

Run `database_init.sql` which includes 5 sample employees

---

## 🔍 View Records

### Attendance Page
- Visit: `http://localhost:8080/attendance.html`
- Shows all employees with their details
- Search, filter, and export to CSV

### Dashboard
- Visit: `http://localhost:8080/2index.html`
- View statistics and recent check-ins
- Department-wise employee distribution

---

## 🐛 Troubleshooting

| Issue | Solution |
|-------|----------|
| Connection refused | Ensure MySQL is running |
| Database not found | Execute `database_init.sql` |
| Port 8080 in use | Change `server.port` in application.properties |
| Email validation error | Use @gmail.com email address |
| Build fails | Ensure Java 17+ and Maven 3.6+ |

---

## 📚 Documentation

For detailed documentation, see:
- **PROJECT_GUIDE.md** - Complete technical guide
- **database_init.sql** - Database setup script
- **application.properties** - Configuration reference

---

## 🔄 GitHub Repository

All code pushed to:  
🔗 https://github.com/Suwetha21/Virtual-Ventures-Emp-App

---

## 📊 Tech Stack

**Backend:** Spring Boot 3.x, Spring Data JPA, MySQL  
**Frontend:** HTML5, CSS3, Vanilla JavaScript  
**Build:** Maven  
**Database:** MySQL 8.0+  
**Java:** JDK 17+  

---

## 📞 Support

For detailed API documentation and SQL queries, see `PROJECT_GUIDE.md`

---

**Status:** ✅ Production Ready  
**Last Updated:** February 2, 2026  
**Version:** 1.0
   - ✓ Your name appears in the list immediately!

5. **View Employee Records**:
   ```
   http://localhost:8080/attendance.html
   ```

---

### Option 2: Register via API (Advanced)

```bash
curl -X POST http://localhost:8080/api/employees/register \
  -H "Content-Type: application/json" \
  -d '{
    "firstName": "John",
    "lastName": "Doe",
    "email": "john.doe@gmail.com",
    "empId": "VV-2024-002",
    "department": "IT",
    "position": "Developer",
    "phoneNumber": "9876543210",
    "dateOfJoining": "2024-01-29",
    "gender": "Male",
    "address": "123 Main St",
    "city": "New York",
    "state": "NY",
    "zipCode": "10001"
  }'
```

**Response (Success):**
```json
{
  "message": "Employee registered successfully",
  "employeeId": 1,
  "empId": "VV-2024-002",
  "email": "john.doe@gmail.com",
  "fullName": "John Doe"
}
```

---

## 📍 Key Features

### Registration Page (`/registration.html`)
- ✓ 13 input fields organized in 3 sections
- ✓ Real-time email validation
- ✓ Automatic error messages
- ✓ Form submission with JSON API call
- ✓ localStorage session management
- ✓ Auto-redirect on success

### Employee Records Page (`/attendance.html`)
- ✓ **Live Table Display**: Shows all registered employees
- ✓ **Search Filter**: Find by Employee ID, Name, or Email
- ✓ **Department Filter**: Filter by selected department
- ✓ **Statistics**: Total employees, today's count, active departments
- ✓ **Actions**:
  - 👁️ View detailed employee information
  - 🗑️ Delete employee record
  - 📥 Export to CSV

---

## 🔍 API Endpoints Reference

### Employees

```
GET    /api/employees                    → Get all employees
GET    /api/employees/{id}              → Get employee by ID
GET    /api/employees/search/{term}     → Search by name
GET    /api/employees/department/{dept} → Filter by department
POST   /api/employees/register          → Register new employee
PUT    /api/employees/{id}              → Update employee
DELETE /api/employees/{id}              → Delete employee
GET    /api/employees/stats/count       → Get total count
```

### Attendance (Legacy)
```
GET    /api/attendance                  → Get all attendance records
POST   /api/attendance/register         → Register attendance
```

---

## 💾 Data Examples

### Departments
- IT
- HR
- Finance
- Sales
- Marketing
- Operations

### Required Fields
- `firstName` - Employee's first name
- `lastName` - Employee's last name
- `email` - Must be @gmail.com
- `empId` - Unique employee ID
- `department` - From list above
- `position` - Job title
- `phoneNumber` - Contact number
- `dateOfJoining` - Start date (YYYY-MM-DD)
- `gender` - Male/Female/Other
- `address` - Street address
- `city` - City name
- `state` - State/Province code
- `zipCode` - Postal code

---

## ✨ What Happens After Registration

1. **Validation** - Email and ID are checked for uniqueness
2. **Storage** - Employee data saved to H2 database
3. **Confirmation** - Success message displayed
4. **Session** - User info stored in localStorage
5. **Redirect** - Automatic navigation to employee records
6. **Display** - New employee appears in the list immediately

---

## 🐛 Troubleshooting

### Issue: "Email already registered"
**Solution**: Use a different email address. Each email must be unique.

### Issue: "Employee ID already exists"
**Solution**: Use a unique employee ID. Format: VV-YYYY-XXX

### Issue: "Email must be @gmail.com"
**Solution**: Make sure your email ends with `@gmail.com` (not @virtualventures.com)

### Issue: Employee doesn't appear in records
**Solution**:
1. Refresh the page (F5)
2. Click "🔄 Refresh" button
3. Check browser console (F12) for errors
4. Verify no validation errors were displayed

### Issue: Server not responding
**Solution**:
1. Check if server is running: `Get-Process | Where-Object Name -match "java"`
2. Restart if needed:
   ```powershell
   cd c:\Users\DELL\Desktop\Practice\employee-portal
   .\mvnw.cmd spring-boot:run
   ```

---

## 📊 Test Data

Here's pre-made test employee data you can use:

```json
{
  "firstName": "Suwetha",
  "lastName": "M",
  "email": "suwetha@gmail.com",
  "empId": "VV-2024-001",
  "department": "IT",
  "position": "Software Engineer",
  "phoneNumber": "9876543210",
  "dateOfJoining": "2024-01-29",
  "gender": "Female",
  "address": "123 Tech Street",
  "city": "Bangalore",
  "state": "KA",
  "zipCode": "560001"
}
```

---

## 🎓 Learning Resources

1. **QUICK_REFERENCE.md** - API examples and quick commands
2. **BACKEND_GUIDE.md** - Detailed API documentation
3. **TESTING_GUIDE.md** - Step-by-step testing procedures
4. **README_BACKEND.md** - Full project overview

---

## 🔗 URLs Reference

```
Registration Form:    http://localhost:8080/registration.html
Employee Records:     http://localhost:8080/attendance.html
API Base:            http://localhost:8080/api
Dashboard:           http://localhost:8080/index.html
```

---

## ✅ Verification Checklist

- [ ] Server is running on port 8080
- [ ] Can access http://localhost:8080/registration.html
- [ ] Can fill registration form without errors
- [ ] Email validation works (rejects non-@gmail.com)
- [ ] Form submits successfully
- [ ] Success message appears
- [ ] Can see new employee in records
- [ ] Employee records page loads without errors
- [ ] Can search/filter employees
- [ ] Can export to CSV

---

## 🎯 Next Steps

1. **Test Registration**: Fill and submit the registration form
2. **View Records**: Navigate to employee records page
3. **Verify Data**: Confirm your employee appears in the list
4. **Explore Features**: Try search, filter, and export
5. **Review Code**: Check out the implementation files

---

## 📞 Support

If you encounter any issues:

1. **Check browser console** (F12) for JavaScript errors
2. **Check server logs** for backend errors
3. **Verify network** - use browser DevTools (Network tab)
4. **Restart server** if needed
5. **Clear cache** - Ctrl+Shift+Delete (Clear browsing data)

---

## 🎉 Success Indicators

You'll know everything is working when:

✓ Registration form submits without errors
✓ Success message appears
✓ Redirected to employee records automatically
✓ New employee appears in the table
✓ Can see employee details
✓ Search and filter work
✓ Export to CSV downloads file

---

**Status**: ✅ Production Ready
**Database**: H2 In-Memory
**API Version**: REST v1
**Build**: Successful (0 errors)
**Last Updated**: January 29, 2026

---

Enjoy your Employee Portal! 🚀
