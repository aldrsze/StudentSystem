# Student Management System (SMS)

A comprehensive, dual-interface Student Management System built with JavaFX and MySQL. This project features a unique "smart launcher" that automatically detects the operating system to provide a seamless Command Line (CLI) or Graphical User Interface (GUI) experience.

## ✨ Features
* **Smart Launcher:** Automatically spawns a native terminal window (Windows, Linux, or macOS) when double-clicking the JAR file.
* **Dual Interface:** Switch between a modern JavaFX GUI or a fast, keyboard-driven CLI.
* **Automated Releases:** Continuous Integration via GitHub Actions to automatically build `.exe` and `.jar` assets.
* **Full CRUD Support:** Manage student records with Create, Read, Update, and Delete capabilities.
* **Responsive Design:** Fluid JavaFX layouts that adapt to window resizing.

## 🛠️ Tech Stack
* **Language:** Java 11+
* **Framework:** JavaFX
* **Build Tool:** Maven
* **Database:** MySQL
* **Deployment:** GitHub Actions & Launch4j

## 🚀 Installation & Setup

### 1. Database Configuration
Create a MySQL database named `student_system` and execute the following:

```sql
CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) UNIQUE,
    email VARCHAR(100) UNIQUE,
    password VARCHAR(255)
);

CREATE TABLE students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    email VARCHAR(100),
    course VARCHAR(100)
);