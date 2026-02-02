# ⚡ Quick Start - 5 Minutes to Running App

## Prerequisites (Already Have?)
- Java 17+: `java -version`
- Maven 3.6+: `mvn -version`  
- MySQL 8.0+: `mysql -version`

---

## 🚀 Run It Now (Copy & Paste)

### 1️⃣ Setup Database (1 min)

```bash
mysql -u root -p
```
Then paste this and hit Enter:
```sql
source database_init.sql;
exit;
```

### 2️⃣ Update Config (1 min)

Edit `application.properties` - Change this line:
```properties
spring.datasource.password=YOUR_MYSQL_PASSWORD
```
Replace `YOUR_MYSQL_PASSWORD` with your MySQL password.

### 3️⃣ Start Application (30 seconds)

**Windows:**
```bash
start.bat
```

**Linux/Mac:**
```bash
./start.sh
```

### 4️⃣ Open in Browser (30 seconds)

**Login Page:**
```
http://localhost:8080
```

**Register Employee:**
```
http://localhost:8080/registration.html
```

**View Records:**
```
http://localhost:8080/attendance.html
```

---

## ✅ You're Done!

The app is now running with:
- ✓ 5 sample employees
- ✓ Login system
- ✓ Registration form
- ✓ Employee records view
- ✓ MySQL database
- ✓ REST API endpoints

---

## 🧪 Test It

### Test 1: Register Employee (1 min)

1. Go to http://localhost:8080/registration.html
2. Fill form:
   - First Name: `Alice`
   - Last Name: `Wonder`
   - Email: `alice@gmail.com` ← **MUST be @gmail.com**
   - Employee ID: `VV-2024-001`
   - Department: `IT`
   - Position: `Developer`
   - Fill rest with any data
3. Click **Register** → ✅ See success message

### Test 2: View Records (30 seconds)

Go to http://localhost:8080/attendance.html → See Alice + 5 sample employees

### Test 3: API (30 seconds)

```bash
curl http://localhost:8080/api/employees
```

You'll see JSON list of all employees.

---

## 🎯 What Each File Does

| File | What it does |
|------|-------------|
| `index.html` | Login page |
| `registration.html` | Register new employee |
| `attendance.html` | View all employees |
| `style.css` | Colors, fonts, layout |
| `function.js` | Login logic |
| `application.properties` | Database connection |
| `database_init.sql` | Create tables + sample data |

---

## 📱 API Endpoints Cheatsheet

```
GET  /api/employees                    → Get all employees
GET  /api/employees/1                  → Get employee #1
POST /api/employees/register           → Register new employee
GET  /api/attendance                   → Get all check-ins
POST /api/attendance/register          → Check in employee
```

---

## ❌ Stuck? Common Fixes

| Error | Fix |
|-------|-----|
| `Connection refused` | MySQL not running |
| `Access denied` | Wrong password in `application.properties` |
| `Port 8080 in use` | Change `server.port=8081` in `application.properties` |
| `Table doesn't exist` | Run `database_init.sql` again |
| `@gmail.com error` | Use email ending with `@gmail.com` |

---

## 📚 Need More Help?

1. **Setup issues?** → Read [SETUP.md](SETUP.md)
2. **API details?** → Read [PROJECT_GUIDE.md](PROJECT_GUIDE.md)
3. **Deploy online?** → Read [DEPLOYMENT.md](DEPLOYMENT.md)
4. **Project overview?** → Read [README.md](README.md)

---

## 🛑 Stop Application

Press **Ctrl+C** in the terminal where it's running.

---

## ✨ That's It!

You now have a full-stack employee management system running locally with:
- Frontend UI (HTML/CSS/JS)
- Backend API (Spring Boot)
- Database (MySQL)
- Authentication (Login)

**Enjoy! 🎉**

---

**Time:** 5 minutes  
**Difficulty:** Easy  
**Status:** ✅ Ready to Use  
**Repo:** https://github.com/Suwetha21/Virtual-Ventures-Emp-App
