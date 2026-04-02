-- Create the database
CREATE DATABASE IF NOT EXISTS student_system;
USE student_system;

-- Table for user authentication (Login/Register)
CREATE TABLE IF NOT EXISTS users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Table for student records
CREATE TABLE IF NOT EXISTS students (
    student_id VARCHAR(20) PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    course VARCHAR(100)
);

-- Insert a default user for testing (User: admin | Pass: 123456)
INSERT IGNORE INTO users (username, password) 
VALUES ('admin', '123456');

-- Insert some sample student data
INSERT IGNORE INTO students (student_id, first_name, last_name, email, course)
VALUES 
('2024-001', 'John', 'Doe', 'john.doe@example.com', 'Computer Science'),
('2024-002', 'Jane', 'Smith', 'jane.smith@example.com', 'Information Technology');