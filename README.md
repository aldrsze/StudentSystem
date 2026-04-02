# Student Management System (SMS)

A professional, dual-interface management system built with **Java 21**, **JavaFX**, and **MySQL**. This project showcases a unique "Smart Launcher" that automatically bridges the gap between modern graphical environments and traditional command-line workflows.

## 📸 Visual Showcase

To demonstrate the dual-interface capabilities, here are the primary views of the system:

<table border="0">
  <tr>
    <td align="center"><b>Modern JavaFX GUI</b></td>
    <td align="center"><b>Native Terminal CLI</b></td>
  </tr>
  <tr>
    <td><img src="screenshots/login_gui.png" width="450" alt="JavaFX Login Screen"></td>
    <td><img src="screenshots/cli_menu.png" width="450" alt="CLI Menu Interface"></td>
  </tr>
  <tr>
    <td colspan="2" align="center"><i>The Smart Launcher automatically detects the environment to provide the best user experience.</i></td>
  </tr>
</table>

## ✨ Key Features

* **Smart Environment Detection:** The system detects if it was launched via a double-click (no console) or a terminal. If double-clicked, it automatically spawns a new native terminal window (Windows, macOS, or Linux) to ensure CLI accessibility.
* **Dual Interface (GUI & CLI):** Users can seamlessly switch between a high-fidelity JavaFX dashboard and a fast, keyboard-driven Command Line Interface.
* **Full CRUD Logic:** Robust backend implementation for Creating, Reading, Updating, and Deleting student records via a MySQL database.
* **Secure Authentication:** Includes a complete User Registration and Login system with input validation.

## 🛠️ Tech Stack

* **Language:** Java 21
* **GUI Framework:** JavaFX 21
* **Database:** MySQL
* **Build Tool:** Maven

## 🚀 How to Use the System

### 1. Launching the Application
The system is designed to be flexible based on how you start it:
* **Standard Launch:** Run the main class `main.fxL`. If you are in an IDE or terminal, it will prompt you to choose between **GUI** or **CLI** mode.
* **Quick Start Arguments:** You can bypass the menu by passing arguments:
    * `java -jar StudentSystem.jar gui` — Launches the Graphical Interface directly.
    * `java -jar StudentSystem.jar cli` — Launches the Command Line Interface directly.

### 2. Navigating the Interfaces
* **GUI Mode:** Use the mouse to navigate the sidebar, fill out student forms, and manage the database table visually.
* **CLI Mode:** Follow the numbered on-screen prompts. The system uses a `Scanner`-based input method for fast data entry and record management.

## ⚙️ Installation & Setup

1.  **Clone the Repository:**
    ```bash
    git clone [https://github.com/your-username/student-management-system.git](https://github.com/your-username/student-management-system.git)
    ```
2.  **Database Setup:** Ensure MySQL is running on `localhost:3306`. Create a database named `student_system` and use the following credentials (standard for this project):
    * **User:** `root`
    * **Password:** `123456`
3.  **Build with Maven:**
    ```bash
    mvn clean install
    ```

---
*Created as a showcase of Java development, UI/UX design, and systems integration.*