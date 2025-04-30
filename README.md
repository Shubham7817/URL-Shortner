# 🔗 URL Shortener Web Application

A full-stack web application that converts long URLs into shortened links for efficient sharing and tracking. The application includes secure user authentication, URL mapping, and click event tracking.

---

## 🚀 Features

- ✅ URL shortening and redirection
- ✅ User registration and login with JWT security
- ✅ Dashboard to view all shortened URLs
- ✅ Click event tracking (analytics)
- ✅ Responsive UI with Tailwind CSS
- ✅ PostgreSQL database with Spring Data JPA

---

## ⚙️ Prerequisites

Make sure these are installed on your system:

- Java 17 or above
- Maven
- Node.js (v16+)
- PostgreSQL
- Git

---

## 🔧 Setup Instructions

### 📦 Backend (Spring Boot)

#### 1. Clone the Repository
```bash
git clone https://github.com/your-username/url-shortener-app.git
cd url-shortener-app/backend

##  Tech Stack

### Frontend
- React.js
- Tailwind CSS

### Backend
- Java 17+
- Spring Boot (MVC, Security)
- Spring Data JPA
- PostgreSQL
- JWT (JSON Web Token)
- Lombok

---

## 🧩 Backend Structure

- **Models**:
  - `User`: Stores user credentials
  - `UrlMapping`: Stores long and shortened URLs with ownership
  - `ClickEvent`: Tracks each click on a shortened URL

- **Security**:
  - JWT-based authentication
  - Spring Security configurations
  - Secured API endpoints

- **Database**: PostgreSQL (Stores users, URLs, and events)

CONFIGURE YOUR DATABASE

--spring.datasource.url=jdbc:postgresql://localhost:5432/your_db_name
--spring.datasource.username=your_username
--spring.datasource.password=your_password
--spring.jpa.hibernate.ddl-auto=update
--jwt.secret=your_jwt_secret

 Install Dependencies & Run

mvn clean install
mvn spring-boot:run

Backend runs at: http://localhost:8080

1. Navigate to frontend

cd ../frontend

2. Install Node Modules

npm install

3. Start Frontend

npm start

Frontend runs at: http://localhost:3000

## Dependencies

# Backend (pom.xml)

spring-boot-starter-web
spring-boot-starter-security
spring-boot-starter-data-jpa
jjwt for JWT token handling
lombok
postgresql JDBC driver

# Frontend (package.json)

react
react-router-dom
axios
tailwindcss
jwt-decode (for decoding JWT tokens)
