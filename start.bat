@echo off
REM Virtual Ventures Employee Portal - Quick Start Script (Windows)
REM This script sets up and runs the application

setlocal enabledelayedexpansion

echo ============================================
echo Virtual Ventures Employee Portal
echo Quick Start Guide
echo ============================================
echo.

REM Check if Maven is installed
mvn --version >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Maven is not installed or not in PATH
    echo Please install Maven from https://maven.apache.org
    pause
    exit /b 1
)

echo [1/4] Checking Maven installation... OK
echo.

REM Check if Java is installed
java -version >nul 2>&1
if errorlevel 1 (
    echo [ERROR] Java is not installed or not in PATH
    echo Please install Java 17 or later
    pause
    exit /b 1
)

echo [2/4] Checking Java installation... OK
echo.

REM Build the application
echo [3/4] Building the application with Maven...
call mvn clean package -DskipTests
if errorlevel 1 (
    echo [ERROR] Build failed
    pause
    exit /b 1
)

echo [4/4] Starting the Spring Boot application...
echo.
echo ============================================
echo Application is starting...
echo ============================================
echo.
echo Access the application at: http://localhost:8080
echo.
echo Pages:
echo   - Login:        http://localhost:8080
echo   - Registration: http://localhost:8080/registration.html
echo   - Attendance:   http://localhost:8080/attendance.html
echo   - Dashboard:    http://localhost:8080/2index.html
echo.
echo API Documentation:
echo   - All Employees: http://localhost:8080/api/employees
echo   - Attendance:    http://localhost:8080/api/attendance
echo.
echo Database Requirements:
echo   - MySQL Server running on localhost:3306
echo   - Database: employee_portal
echo   - Execute: database_init.sql before first run
echo.
echo Press Ctrl+C to stop the server
echo ============================================
echo.

java -jar target/employee-portal-*.jar

pause
