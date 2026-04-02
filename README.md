# Student Management System (SMS)

A comprehensive, dual-interface (JavaFX GUI & Console CLI) Student Management System designed to handle student records and administrative user authentication seamlessly. 

## ✨ Features
* **Dual Interface:** Run the application using a modern graphical interface (JavaFX) or a fast command-line interface (CLI).
* **Secure Authentication:** User login and registration system.
* **Dashboard Analytics:** Real-time tracking of total students and active courses.
* **Student CRUD Operations:** * Register new students.
  * View all student records in a dynamic table.
  * Update existing student information.
  * Safely delete records with confirmation prompts.
* **Responsive UI:** Fully responsive JavaFX design with animated panels and hover effects.

## 🛠️ Technologies Used
* **Language:** Java
* **UI Framework:** JavaFX
* **Build Tool:** Maven
* **Database:** MySQL
* **Driver:** MySQL Connector/J

## 🚀 Getting Started

### Prerequisites
To run this project, you will need to have the following installed:
* [Java Development Kit (JDK)](https://www.oracle.com/java/technologies/downloads/) (Version 11 or higher)
* [Maven](https://maven.apache.org/)
* [MySQL Server](https://dev.mysql.com/downloads/mysql/)

### Database Setup
Before running the application, you must configure the MySQL database. 

1. Open your MySQL client (e.g., MySQL Workbench or Command Line).
2. Execute the following SQL script to create the database and required tables:

```sql
CREATE DATABASE IF NOT EXISTS sms_db;
USE sms_db;

-- Table for Admin Users
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL
);

-- Table for Students
CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) NOT NULL,
    course VARCHAR(100) NOT NULL
);