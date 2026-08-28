# AddressBook – Java Microservices Contact Manager

A modern Address Book application built for the Netex Java Developer assignment.

## Tech Stack

### Backend
- Java 25
- Spring Boot 4.1
- Spring Security + JWT
- Spring Data JPA
- PostgreSQL
- Flyway
- Apache Kafka
- JUnit 5 + Mockito

### Frontend
- React
- Material UI
- Axios
- React Router

### Infrastructure
- Docker
- Docker Compose
- Kafka UI
- PgAdmin

---

# Architecture

```
React Frontend (localhost:5173)
            │
            │ HTTP + JWT
            ▼
     Contact Service (8082)
            │
            │ HTTP
            ▼
      User Service (8081)
            │
            │ Kafka
            ▼
      user.signup Topic
```

The application contains two microservices communicating over HTTP.

- **user-service** – authentication, JWT, user registration, Kafka producer/consumer
- **contact-service** – contact management, search, CSV export

---

# Features

- User registration
- JWT login
- Kafka event on signup
- Public contact listing
- Create contact
- Edit own contacts
- Delete own contacts
- Search by name
- CSV export
- Picture URL support
- Address support
- Global exception handling
- Unit tests

---

# Contact model

| Field | Type |
|--------|------|
| Picture | String (URL) |
| First Name | String |
| Last Name | String |
| Phone | String |
| Email | String |
| Company | String |
| Address | String |

---

# Database

Two PostgreSQL databases are used.

| Database | Service |
|----------|---------|
| addressbook_users | user-service |
| addressbook_contacts | contact-service |

Flyway automatically creates the schema on startup.

---

# Running the project

## 1. Start Docker

```bash
docker compose up -d
```

This starts:

- PostgreSQL
- Kafka
- Kafka UI
- PgAdmin

## 2. Start backend

```bash
cd user-service
mvn spring-boot:run
```

New terminal:

```bash
cd contact-service
mvn spring-boot:run
```

## 3. Start frontend

```bash
cd frontend
npm install
npm start
```

Application:

| Service | URL |
|----------|-----|
| React | http://localhost:5173 |
| User API | http://localhost:8081 |
| Contact API | http://localhost:8082 |
| Kafka UI | http://localhost:8080 |
| PgAdmin | http://localhost:5050 |

---

# API examples

## Register

```http
POST /api/auth/register
```

```json
{
  "email":"alex@test.com",
  "password":"123456"
}
```

## Login

```http
POST /api/auth/login
```

Returns JWT token.

## Create contact

```http
POST /api/contacts
Authorization: Bearer <TOKEN>
```

```json
{
  "firstName":"John",
  "lastName":"Doe",
  "phone":"0712345678",
  "email":"john@test.com",
  "company":"Netex",
  "address":"Bucharest",
  "picture":"https://i.pravatar.cc/300?img=12"
}
```

---

# CSV Export

The export contains:

- First Name
- Last Name
- Phone
- Email
- Company
- Address
- Picture URL

---

# Security

- JWT authentication
- Stateless sessions
- Password hashing using BCrypt
- Users may edit/delete **only their own contacts**
- Contact listing is public

---

# Testing

Run all tests:

```bash
mvn test
```

Included:

- ContactService unit tests
- ContactController WebMvc tests

---

# Author

Aleksandar Risteski
