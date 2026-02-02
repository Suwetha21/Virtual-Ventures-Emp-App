# 🌐 Deployment Guide - Multiple Options

## Deployment Options Overview

| Option | Type | Cost | Setup Time | Best For |
|--------|------|------|-----------|----------|
| **Localhost** | Local | Free | 5 min | Development & Testing |
| **GitHub Pages** | Static Host | Free | 10 min | Frontend only (HTML/CSS/JS) |
| **Heroku** | Cloud Platform | Free-$7/mo | 15 min | Full Stack (Backend + Frontend) |
| **AWS** | Cloud Services | $5-20/mo | 20 min | Production + Scaling |
| **DigitalOcean** | Cloud Droplet | $5/mo | 20 min | Simple VPS Hosting |
| **Railway** | Modern PaaS | $5/mo | 10 min | Quick Deploy (Full Stack) |

---

## Option 1: Localhost Development (Default Setup)

### Best For: Development, Testing, Learning

### Prerequisites
- Java 17+
- Maven
- MySQL running

### Steps

1. **Start MySQL:**
   ```bash
   # Windows: Start-Service MySQL80
   # Linux: sudo systemctl start mysql
   # Mac: brew services start mysql
   ```

2. **Setup Database:**
   ```bash
   mysql -u root -p < database_init.sql
   ```

3. **Run Application:**
   ```bash
   ./start.bat      # Windows
   ./start.sh       # Linux/Mac
   ```

4. **Access:**
   - Open http://localhost:8080
   - Login and test features

### Stopping Server
- Press **Ctrl+C** in the terminal running the server

---

## Option 2: GitHub Pages (Frontend Only)

### Best For: Static website hosting, Portfolio, Demo

### Prerequisites
- GitHub account
- All files already in root directory

### Steps

1. **Enable GitHub Pages in Repository Settings:**
   - Go to https://github.com/Suwetha21/Virtual-Ventures-Emp-App/settings
   - Scroll to "GitHub Pages" section
   - Select branch: `main`
   - Select folder: `/ (root)`
   - Click "Save"

2. **Wait for Deployment:**
   - Takes 1-2 minutes
   - Green checkmark appears

3. **Access Website:**
   ```
   https://suwetha21.github.io/Virtual-Ventures-Emp-App/
   ```

### Limitations
- ❌ Backend APIs won't work (no server)
- ❌ Database won't function
- ✅ Only frontend pages display

### To Make It Work
You need a backend server for full functionality. See **Option 3** or **Option 4** below.

---

## Option 3: Heroku Deployment (Full Stack)

### Best For: Free/cheap full-stack hosting with cloud database

### Prerequisites
- Heroku account (free at https://www.heroku.com)
- Git installed

### Step 1: Create Heroku App

```bash
heroku login
heroku create your-app-name-employee-portal
```

### Step 2: Add JawsDB (Free MySQL)

```bash
heroku addons:create jawsdb:kitefin
```

This automatically sets environment variables for MySQL connection.

### Step 3: Update application.properties

Replace MySQL config with environment variables:

```properties
spring.datasource.url=${JAWSDB_URL}
spring.datasource.username=${JAWSDB_USERNAME}
spring.datasource.password=${JAWSDB_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
```

Or create **Procfile** in root:

```
web: java -Dserver.port=$PORT -jar target/employee-portal-0.0.1-SNAPSHOT.jar
```

### Step 4: Deploy

```bash
git push heroku main
```

### Step 5: Access

```
https://your-app-name-employee-portal.herokuapp.com
```

### Enable Database

```bash
heroku run mysql -u root < database_init.sql
```

---

## Option 4: Railway Deployment (Recommended - Easiest)

### Best For: Quick, modern full-stack deployment

### Prerequisites
- Railway account (https://railway.app)
- GitHub connected

### Step 1: Connect Repository

1. Go to https://railway.app
2. Click "New Project"
3. Select "Deploy from GitHub repo"
4. Select: `Suwetha21/Virtual-Ventures-Emp-App`

### Step 2: Add MySQL Database

1. In Railway dashboard, click "Add"
2. Select "MySQL"
3. Railway auto-creates database URL

### Step 3: Configure Environment Variables

Add in Railway dashboard:

```
DATABASE_URL=mysql://[user]:[password]@[host]:3306/employee_portal
SPRING_DATASOURCE_URL=jdbc:mysql://[host]:3306/employee_portal
SPRING_DATASOURCE_USERNAME=[user]
SPRING_DATASOURCE_PASSWORD=[password]
```

### Step 4: Deploy

- Railway automatically deploys on git push
- Takes ~2-3 minutes

### Step 5: Access

```
https://[project-id].railway.app
```

---

## Option 5: AWS Elastic Beanstalk (Production Grade)

### Best For: Enterprise, scalability, monitoring

### Prerequisites
- AWS account
- AWS CLI installed

### Steps

```bash
# Install EB CLI
pip install awsebcli

# Initialize
eb init -p "Java 17 running on 64bit Amazon Linux 2"

# Create environment
eb create employee-portal-env

# Deploy
eb deploy

# Open in browser
eb open
```

---

## Option 6: DigitalOcean App Platform

### Best For: Simple VPS with Docker

### Prerequisites
- DigitalOcean account ($5/month)
- GitHub connected

### Step 1: Create App

1. DigitalOcean Dashboard → Apps → Create App
2. Select GitHub repo
3. Select branch: `main`

### Step 2: Configure

- Runtime: Java
- Build Command: `mvn clean package -DskipTests`
- Run Command: `java -jar target/employee-portal-*.jar`

### Step 3: Add Database

- Click "Add Resource"
- Choose "MySQL"
- Connect to app

### Step 4: Deploy

- Click "Deploy"
- Takes ~5-10 minutes

### Access

```
https://[app-name]-[random].ondigitalocean.app
```

---

## Docker Containerization (For Any Cloud)

### Prerequisites
- Docker installed

### Step 1: Create Dockerfile

```dockerfile
FROM maven:3.8.1-jdk-17 AS build
WORKDIR /build
COPY . .
RUN mvn clean package -DskipTests

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /build/target/employee-portal-*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
```

### Step 2: Build Image

```bash
docker build -t employee-portal:latest .
```

### Step 3: Run Container

```bash
docker run -p 8080:8080 \
  -e SPRING_DATASOURCE_URL=jdbc:mysql://mysql:3306/employee_portal \
  -e SPRING_DATASOURCE_USERNAME=root \
  -e SPRING_DATASOURCE_PASSWORD=password \
  employee-portal:latest
```

### Step 4: Docker Compose (Full Stack)

Create `docker-compose.yml`:

```yaml
version: '3'
services:
  mysql:
    image: mysql:8.0
    environment:
      MYSQL_ROOT_PASSWORD: password
      MYSQL_DATABASE: employee_portal
    volumes:
      - ./database_init.sql:/docker-entrypoint-initdb.d/init.sql
    ports:
      - "3306:3306"

  app:
    build: .
    depends_on:
      - mysql
    environment:
      SPRING_DATASOURCE_URL: jdbc:mysql://mysql:3306/employee_portal
      SPRING_DATASOURCE_USERNAME: root
      SPRING_DATASOURCE_PASSWORD: password
    ports:
      - "8080:8080"
```

Run: `docker-compose up`

---

## Deployment Comparison Table

| Feature | Heroku | Railway | AWS | DigitalOcean | Docker |
|---------|--------|---------|-----|--------------|--------|
| **Cost** | Free-7$/mo | Free-5$/mo | ~20$/mo | 5$/mo | Varies |
| **Setup** | 15 min | 10 min | 30 min | 15 min | 20 min |
| **Ease** | Easy | Very Easy | Hard | Medium | Medium |
| **Uptime** | 99.9% | 99.9% | 99.95% | 99.9% | 99.9% |
| **Scalability** | Good | Good | Excellent | Good | Excellent |
| **Custom Domain** | Yes | Yes | Yes | Yes | Yes |
| **SSL/HTTPS** | Yes | Yes | Yes | Yes | Yes |
| **Monitoring** | Basic | Good | Excellent | Good | Required |

---

## Custom Domain Setup

For any deployment option:

### Step 1: Buy Domain
- GoDaddy, Namecheap, Route53, etc.

### Step 2: Point to Your Server

**For GitHub Pages:**
```
Add CNAME file in root with: suwetha21.github.io
Update domain DNS records:
  CNAME: CNAME  username.github.io
```

**For Heroku/Railway/AWS:**
```
Update domain DNS records:
  CNAME: [your-app-url]
OR
  A Record: [IP address provided by host]
```

### Step 3: Enable HTTPS
- Usually automatic (Let's Encrypt)
- May take 24-48 hours for DNS propagation

---

## Environment Variables by Platform

### Heroku
```bash
heroku config:set SPRING_DATASOURCE_URL="jdbc:mysql://..."
heroku config:set SPRING_DATASOURCE_USERNAME="root"
heroku config:set SPRING_DATASOURCE_PASSWORD="password"
```

### Railway
```
Set in dashboard → Variables
DATABASE_URL=mysql://...
```

### AWS Elastic Beanstalk
```bash
eb setenv SPRING_DATASOURCE_URL="jdbc:mysql://..."
```

### DigitalOcean
```
Set in app spec or dashboard
```

---

## Monitoring & Maintenance

### Check Logs

**Heroku:**
```bash
heroku logs --tail
```

**Railway:**
- View in dashboard

**AWS:**
- CloudWatch → Logs

### Database Backups

**Important:** Set up automatic backups!

**Heroku (JawsDB):**
- Automatic daily backup

**Railway:**
- Manual backup in dashboard

**AWS:**
- RDS automatic snapshots

---

## Troubleshooting Deployments

### ❌ Database Connection Failed

```
Check:
- Database is running
- Credentials correct in environment variables
- Database initialized with database_init.sql
- Network/firewall allows connection
```

### ❌ Application Not Starting

```
Check:
- Java 17+ available on server
- Maven build successful locally
- All dependencies in pom.xml
- Port 8080 not in use
- Sufficient disk space
```

### ❌ Slow Performance

```
Solutions:
- Add caching layer
- Optimize database queries
- Use CDN for static files
- Scale up compute resources
```

---

## Recommended Production Setup

### For Best Performance & Cost:

1. **Frontend:** GitHub Pages (Free)
   - Serves HTML/CSS/JS from CDN

2. **Backend:** Railway or DigitalOcean ($5-10/mo)
   - Runs Spring Boot app

3. **Database:** Railway or AWS RDS ($5-15/mo)
   - Managed MySQL database

4. **Domain:** Any registrar ($10-15/year)
   - Custom domain name

5. **Monitoring:** Railway dashboard + Logs
   - Track performance

**Total Monthly Cost:** $10-25/month  
**Uptime:** 99.9%  
**Ease:** Medium

---

## One-Command Deploy (Railway)

```bash
git push origin main
# Railway auto-deploys!
# Check https://railway.app dashboard
```

---

## Next Steps

1. Choose deployment option (Recommended: Railway)
2. Follow step-by-step guide above
3. Test all features after deployment
4. Setup monitoring/backups
5. Configure custom domain (optional)

---

**Documentation:** February 2, 2024  
**Recommended:** Railway Platform  
**GitHub Repo:** https://github.com/Suwetha21/Virtual-Ventures-Emp-App
