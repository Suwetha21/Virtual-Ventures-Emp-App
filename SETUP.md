# 🔧 Complete Setup Instructions - Step by Step

## Prerequisites Checklist

Before starting, ensure you have installed:

- [ ] **Java 17 or higher** - [Download Java](https://www.oracle.com/java/technologies/downloads/)
  ```bash
  java -version
  ```

- [ ] **Maven 3.6 or higher** - [Download Maven](https://maven.apache.org/download.cgi)
  ```bash
  mvn -version
  ```

- [ ] **MySQL Server 8.0 or higher** - [Download MySQL](https://www.mysql.com/downloads/)
  ```bash
  mysql --version
  ```

---

## Step 1: MySQL Database Setup

### Option A: Using MySQL Command Line

1. **Open Command Prompt/Terminal** and login to MySQL:
   ```bash
   mysql -u root -p
   # Enter your MySQL password when prompted
   ```

2. **Create Database and Tables** by pasting the contents of `database_init.sql`:
   ```sql
   CREATE DATABASE IF NOT EXISTS employee_portal;
   USE employee_portal;
   
   -- Paste entire contents of database_init.sql here
   ```

### Option B: Using MySQL Workbench (GUI)

1. Open **MySQL Workbench**
2. Connect to your MySQL server
3. Go to **File** → **Open SQL Script**
4. Select `database_init.sql` file
5. Click **Execute** button or press **Ctrl+Shift+Enter**
6. Wait for completion message

### Option C: Using Command Line Script

```bash
# On Windows:
mysql -u root -p < database_init.sql

# On Linux/Mac:
mysql -u root -p employee_portal < database_init.sql
```

### Verify Database Setup

```sql
-- Connect to database
USE employee_portal;

-- Check tables exist
SHOW TABLES;

-- Should show:
-- attendance
-- employees

-- Check sample data
SELECT COUNT(*) as employee_count FROM employees;
SELECT COUNT(*) as attendance_count FROM attendance;
```

---

## Step 2: Configure Application Properties

Edit the file `application.properties` in your project root:

```properties
# Server Configuration
server.port=8080
server.servlet.context-path=/

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/employee_portal
spring.datasource.username=root
spring.datasource.password=YOUR_MYSQL_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# Hibernate Configuration
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect

# Logging
logging.level.root=INFO
```

**⚠️ IMPORTANT:** Replace `YOUR_MYSQL_PASSWORD` with your actual MySQL password set during installation.

---

## Step 3: Build the Application

### Using Maven

```bash
# Windows
mvnw clean package -DskipTests

# Linux/Mac
./mvnw clean package -DskipTests
```

This will:
- Clean previous builds
- Download all dependencies
- Compile Java code
- Package into JAR file
- Create `target/` folder with `.jar` file

**Expected Output:**
```
[INFO] Building jar: target/employee-portal-0.0.1-SNAPSHOT.jar
[INFO] BUILD SUCCESS
```

---

## Step 4: Run the Application

### Option A: Quick Start Scripts (Recommended)

**Windows:**
```bash
start.bat
```

**Linux/Mac:**
```bash
chmod +x start.sh
./start.sh
```

### Option B: Manual Execution

```bash
# Windows & Linux/Mac
java -jar target/employee-portal-0.0.1-SNAPSHOT.jar
```

### Option C: Direct Maven Command

```bash
mvn spring-boot:run
```

### Success Indicators

When application starts successfully, you should see:
```
  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_|\__, | / / / /
 =========|_|==============|___/=/_/_/_/

Tomcat started on port(s): 8080 (http)
Employee Portal is running successfully!
```

---

## Step 5: Access the Application

Open your web browser and navigate to:

| Feature | URL | Description |
|---------|-----|-------------|
| 🔐 **Login Page** | http://localhost:8080 | Main entry point |
| 📝 **Register** | http://localhost:8080/registration.html | Create new employee |
| 👥 **Employee Records** | http://localhost:8080/attendance.html | View all employees |
| 📊 **Dashboard** | http://localhost:8080/2index.html | Statistics & insights |

---

## Step 6: Test the Application

### Test 1: Register an Employee (Web Form)

1. Go to: http://localhost:8080/registration.html
2. Fill in all fields:
   - First Name: `John`
   - Last Name: `Doe`
   - Email: `john@gmail.com` (must end with @gmail.com)
   - Employee ID: `VV-2024-100`
   - Department: `IT`
   - Position: `Senior Developer`
   - Other fields: Fill with test data
3. Click **Register**
4. See confirmation message ✓

### Test 2: View Employee Records

1. Go to: http://localhost:8080/attendance.html
2. You should see:
   - Your newly registered employee
   - Sample employees from database_init.sql
   - Total count and department filters

### Test 3: Test API Endpoints

**Get All Employees:**
```bash
curl http://localhost:8080/api/employees
```

**Register via API:**
```bash
curl -X POST http://localhost:8080/api/employees/register \
  -H "Content-Type: application/json" \
  -d "{
    \"firstName\": \"Jane\",
    \"lastName\": \"Smith\",
    \"email\": \"jane@gmail.com\",
    \"empId\": \"VV-2024-101\",
    \"department\": \"HR\",
    \"position\": \"Manager\",
    \"phoneNumber\": \"9876543210\",
    \"dateOfJoining\": \"2024-02-15\",
    \"gender\": \"Female\",
    \"address\": \"456 Oak Ave\",
    \"city\": \"Boston\",
    \"state\": \"MA\",
    \"zipCode\": \"02101\"
  }"
```

**Check Attendance:**
```bash
curl http://localhost:8080/api/attendance
```

---

## Troubleshooting

### ❌ MySQL Connection Error: "Unable to acquire JDBC Connection"

**Cause:** MySQL not running or wrong credentials

**Solution:**
```bash
# Check if MySQL is running
# Windows: Check Services (services.msc)
# Linux: sudo systemctl status mysql
# Mac: brew services list | grep mysql

# Restart MySQL
# Windows: net start MySQL80 (or your version)
# Linux: sudo systemctl restart mysql
# Mac: brew services restart mysql

# Verify credentials in application.properties
```

### ❌ Build Fails: "Maven not found"

**Cause:** Maven not in PATH

**Solution:**
```bash
# Use Maven wrapper instead
mvnw clean package -DskipTests  # Windows
./mvnw clean package -DskipTests # Linux/Mac
```

### ❌ Port 8080 Already in Use

**Cause:** Another application using port 8080

**Solution A:** Kill the process using port 8080
```bash
# Windows
netstat -ano | findstr :8080
taskkill /PID <PID> /F

# Linux/Mac
lsof -ti:8080 | xargs kill -9
```

**Solution B:** Change port in `application.properties`
```properties
server.port=8081  # Use different port
```

### ❌ Email Validation Error

**Cause:** Email doesn't end with @gmail.com

**Solution:** Use valid @gmail.com email addresses only
- ✓ john@gmail.com
- ✗ john@yahoo.com
- ✗ john@company.com

### ❌ Database Table Not Found

**Cause:** `database_init.sql` not executed

**Solution:** Execute the SQL file:
```bash
mysql -u root -p employee_portal < database_init.sql
```

---

## Project Structure

```
employee-portal/
├── 📄 index.html                 # Login page
├── 📄 registration.html          # Registration form
├── 📄 attendance.html            # Employee records
├── 📄 2index.html                # Dashboard
├── 🎨 style.css                  # Stylesheet
├── 📝 function.js                # JavaScript functions
├── ☕ EmployeePortalApplication.java
├── ☕ Employee.java
├── ☕ Attendance.java
├── ☕ EmployeeController.java
├── ☕ AttendanceController.java
├── ☕ EmployeeRepository.java
├── ☕ PageController.java
├── 📋 application.properties      # App config
├── 📦 pom.xml                    # Maven config
├── 🗄️ database_init.sql         # Database schema & sample data
├── 📚 README.md                  # Quick start guide
├── 📖 PROJECT_GUIDE.md           # Detailed documentation
├── 🚀 start.bat                  # Windows startup script
├── 🚀 start.sh                   # Linux/Mac startup script
└── 🔧 SETUP.md                   # This file
```

---

## File Descriptions

| File | Purpose |
|------|---------|
| `index.html` | Login entry point |
| `registration.html` | Employee registration form |
| `attendance.html` | View employee records |
| `2index.html` | Dashboard with statistics |
| `style.css` | All styles (gradient theme #667eea-#764ba2) |
| `function.js` | Login & navigation logic |
| `EmployeePortalApplication.java` | Spring Boot entry point |
| `Employee.java` | Employee data model |
| `Attendance.java` | Attendance data model |
| `EmployeeController.java` | REST APIs for employees |
| `AttendanceController.java` | REST APIs for attendance |
| `EmployeeRepository.java` | Database queries for employees |
| `PageController.java` | Page routing & forwarding |
| `application.properties` | Database & server config |
| `pom.xml` | Maven dependencies |
| `database_init.sql` | Create tables + sample data |
| `README.md` | Project overview |
| `PROJECT_GUIDE.md` | Complete technical documentation |
| `start.bat` | Run on Windows |
| `start.sh` | Run on Linux/Mac |

---

## Database Schema

### employees table
```sql
- id (Primary Key)
- first_name
- last_name
- email (Unique)
- emp_id (Unique)
- department
- position
- phone_number
- date_of_joining
- gender
- address
- city
- state
- zip_code
- registration_date
```

### attendance table
```sql
- id (Primary Key)
- emp_name
- emp_id
- emp_email
- check_in_time
- created_at
```

---

## Next Steps

1. ✅ Install Java, Maven, MySQL
2. ✅ Run `database_init.sql` in MySQL
3. ✅ Update `application.properties` with MySQL credentials
4. ✅ Run `start.bat` (Windows) or `start.sh` (Linux/Mac)
5. ✅ Open http://localhost:8080
6. ✅ Register test employees
7. ✅ Test all features

---

## Support & Documentation

For more information:
- 📖 See **README.md** for quick start
- 📚 See **PROJECT_GUIDE.md** for detailed API docs
- 🐛 Check **Troubleshooting** section above

---

## Technology Stack

| Component | Technology |
|-----------|-----------|
| **Backend** | Spring Boot 3.x |
| **Database** | MySQL 8.0+ |
| **Frontend** | HTML5 + CSS3 + Vanilla JS |
| **Build Tool** | Maven |
| **ORM** | Hibernate + Spring Data JPA |
| **Java Version** | JDK 17+ |

---

**Created:** February 2, 2024  
**Status:** ✅ Production Ready  
**Version:** 1.0  
**GitHub:** https://github.com/Suwetha21/Virtual-Ventures-Emp-App
