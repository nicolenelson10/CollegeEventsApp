-- College Events Database Setup Script
-- Run this script after installing MySQL

-- Create the database
CREATE DATABASE IF NOT EXISTS college_events_db;
USE college_events_db;

-- Create users table
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role ENUM('student', 'admin') NOT NULL DEFAULT 'student',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create events table
CREATE TABLE IF NOT EXISTS events (
    eventID INT AUTO_INCREMENT PRIMARY KEY,
    eventName VARCHAR(200) NOT NULL,
    date DATE NOT NULL,
    time TIME NOT NULL,
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Create registrations table
CREATE TABLE IF NOT EXISTS registrations (
    registrationID INT AUTO_INCREMENT PRIMARY KEY,
    userID INT NOT NULL,
    eventID INT NOT NULL,
    registrationDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (userID) REFERENCES users(id) ON DELETE CASCADE,
    FOREIGN KEY (eventID) REFERENCES events(eventID) ON DELETE CASCADE,
    UNIQUE KEY unique_registration (userID, eventID)
);

-- Insert sample data
-- Sample admin user (password: admin123)
INSERT IGNORE INTO users (name, email, password, role) VALUES 
('Admin User', 'admin@college.edu', 'admin123', 'admin');

-- Sample student users (password: student123)
INSERT IGNORE INTO users (name, email, password, role) VALUES 
('John Doe', 'john.doe@student.edu', 'student123', 'student'),
('Jane Smith', 'jane.smith@student.edu', 'student123', 'student'),
('Mike Johnson', 'mike.johnson@student.edu', 'student123', 'student');

-- Sample events
INSERT IGNORE INTO events (eventName, date, time, description) VALUES 
('Welcome Orientation', '2024-11-15', '09:00:00', 'Welcome new students to the college'),
('Career Fair', '2024-11-20', '10:00:00', 'Meet potential employers and explore career opportunities'),
('Tech Workshop', '2024-11-25', '14:00:00', 'Learn about latest technologies and programming languages'),
('Sports Day', '2024-12-01', '08:00:00', 'Annual college sports competition'),
('Cultural Festival', '2024-12-10', '18:00:00', 'Celebrate diversity with music, dance, and food');

-- Sample registrations
INSERT IGNORE INTO registrations (userID, eventID) VALUES 
(2, 1), (2, 2), (3, 1), (3, 3), (4, 2), (4, 4);

-- Display created tables
SHOW TABLES;
SELECT 'Database setup completed successfully!' as Status;