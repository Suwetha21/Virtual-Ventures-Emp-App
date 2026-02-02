#!/bin/bash
# Virtual Ventures Employee Portal - Quick Start Script (Linux/Mac)

echo "============================================"
echo "Virtual Ventures Employee Portal"
echo "Quick Start Guide"
echo "============================================"
echo ""

# Check if Maven is installed
if ! command -v mvn &> /dev/null; then
    echo "[ERROR] Maven is not installed or not in PATH"
    echo "Please install Maven from https://maven.apache.org"
    exit 1
fi

echo "[1/4] Checking Maven installation... OK"
echo ""

# Check if Java is installed
if ! command -v java &> /dev/null; then
    echo "[ERROR] Java is not installed or not in PATH"
    echo "Please install Java 17 or later"
    exit 1
fi

echo "[2/4] Checking Java installation... OK"
echo ""

# Build the application
echo "[3/4] Building the application with Maven..."
mvn clean package -DskipTests
if [ $? -ne 0 ]; then
    echo "[ERROR] Build failed"
    exit 1
fi

echo "[4/4] Starting the Spring Boot application..."
echo ""
echo "============================================"
echo "Application is starting..."
echo "============================================"
echo ""
echo "Access the application at: http://localhost:8080"
echo ""
echo "Pages:"
echo "  - Login:        http://localhost:8080"
echo "  - Registration: http://localhost:8080/registration.html"
echo "  - Attendance:   http://localhost:8080/attendance.html"
echo "  - Dashboard:    http://localhost:8080/2index.html"
echo ""
echo "API Documentation:"
echo "  - All Employees: http://localhost:8080/api/employees"
echo "  - Attendance:    http://localhost:8080/api/attendance"
echo ""
echo "Database Requirements:"
echo "  - MySQL Server running on localhost:3306"
echo "  - Database: employee_portal"
echo "  - Execute: database_init.sql before first run"
echo ""
echo "Press Ctrl+C to stop the server"
echo "============================================"
echo ""

java -jar target/employee-portal-*.jar
