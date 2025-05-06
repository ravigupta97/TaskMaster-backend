# 🧠 TaskMaster Backend

Welcome to the backend of **TaskMaster**, a powerful and secure Smart Task Manager built with **Spring Boot** and **PostgreSQL**, designed for managing tasks efficiently with role-based access control and insightful analytics.

## 🚀 Live Demo

Backend: [https://smart-task-manager-backend.onrender.com](https://smart-task-manager-backend.onrender.com)  
Frontend: [https://taskmaster-frontend.netlify.app](https://taskmaster-frontend.netlify.app)

## 📌 Features

- 🔐 **JWT Authentication** (Login / Register)
- 👥 **Role-Based Access Control** (Admin / Manager / Employee)
- 📊 **Task Analytics** (e.g., status breakdown, task completion trends, user productivity)
- 🧾 **Task CRUD APIs**
- 🗂️ **Filter Tasks by User or Date**
- 🌍 CORS Enabled for Frontend Integration
- 📦 RESTful JSON APIs

---

## 🛠️ Technologies Used

| Tech | Description |
|------|-------------|
| `Java 17` | Modern Java version |
| `Spring Boot` | Backend framework |
| `Spring Security` | Authentication & Authorization |
| `JWT` | Token-based auth |
| `PostgreSQL` | Relational database |
| `Maven` | Dependency management |
| `Render` | Backend hosting platform |

---

## 🛠️ Project Structure

```
src/
├── config/           # JWT Security Configuration and Filters
├── controller/       # REST API Controllers for Auth, Tasks, Analytics
├── dto/              # Data Transfer Objects (LoginRequest, TaskDTO, etc.)
├── entity/           # JPA Entities like User, Role, Task
├── exception/        # Custom Exception Handling (GlobalExceptionHandler, etc.)
├── repository/       # Spring Data JPA Repositories
├── service/          # Service Layer - Business Logic and JWT Service
└── TaskManagerApp.java  # Main Spring Boot Application Entry Point
```

---

## 📡 API Endpoints

### 🔐 Authentication
| Method | Endpoint | Description |
|--------|----------|-------------|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and get JWT token |

### ✅ Task Management (Authenticated Depending on Role)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/tasks` | Get all tasks |
| GET | `/api/tasks/my` | Get all tasks of logged-In User |
| POST | `/api/tasks` | Create a new task |
| PUT | `/api/tasks/{id}` | Update a task |
| PUT | `/api/tasks/{id}/status` | Update status of the task |
| DELETE | `/api/tasks/{id}` | Delete a task |

### 📊 Analytics (Admin Only)
| Method | Endpoint | Description |
|--------|----------|-------------|
| GET | `/api/analytics/task-summary` | Task analytics |
| GET | `/api/analytics/user-performance-summary` | User productivity analytics |
| GET | `/api/analytics/due-date-summary` | Due Date related analytics |
| GET | `/api/analytics/status-summary` | Task status analytics |

---

## 📦 Getting Started (Local Setup)

### 🔧 Prerequisites
- Java 17+
- Spring Boot
- Maven
- PostgreSQL

### 🧪 Clone and Run

```bash
git clone https://github.com/ravigupta97/TaskMaster-backend.git
cd TaskMaster-backend

