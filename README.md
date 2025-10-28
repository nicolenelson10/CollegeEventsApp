# SJCET Event Management System

A comprehensive event management system for St. Joseph's College of Engineering and Technology.

## Features
- ✅ User Authentication (Student/Admin)
- ✅ Event Registration System
- ✅ Event Management (CRUD Operations)
- ✅ Email Notifications
- ✅ PDF Export
- ✅ Attendance Tracking
- ✅ Password Security
- ✅ Professional UI

## Prerequisites
- Java 8 or higher
- MySQL Database (XAMPP recommended)
- VS Code with Java Extension Pack

## Setup Instructions

### 1. Database Setup
1. Start XAMPP and run Apache + MySQL
2. Open phpMyAdmin (http://localhost/phpmyadmin)
3. Create database `college_events_db`
4. Run the SQL script from `database_setup.sql`

### 2. Running in VS Code
1. Open this folder in VS Code
2. Install "Extension Pack for Java" if not installed
3. Press F5 or click Run → Start Debugging
4. Or use Ctrl+Shift+P → "Tasks: Run Task" → "Run SJCET Event Management"

## Login Credentials
- **Student**: `student1@college.com` / `student123`
- **Admin**: `admin@college.com` / `admin123`

## Project Structure
```
CollegeEventsApp/
├── src/com/collegeeventapp/
│   ├── controller/     # Business logic controllers
│   ├── dao/           # Database access objects
│   ├── model/         # Data models
│   ├── util/          # Utility classes
│   ├── view/          # UI components
│   └── AppLauncher.java
├── lib/               # External libraries
├── .vscode/           # VS Code configuration
└── database_setup.sql # Database schema
```

## Technologies Used
- Java Swing (GUI)
- MySQL (Database)
- JDBC (Database Connectivity)