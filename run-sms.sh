#!/bin/bash

echo "=================================================="
echo "     Student Management System Launcher (Maven)   "
echo "=================================================="

# Check for Maven and install if not present
if ! command -v mvn &> /dev/null
then
    echo "[!] Maven is not installed. Attempting to install it..."
    
    # Detect package manager and install Maven
    if command -v apt-get &> /dev/null; then
        echo "--> Detected apt-get (Debian/Ubuntu). Installing Maven..."
        sudo apt-get update
        sudo apt-get install -y maven
    elif command -v yum &> /dev/null; then
        echo "--> Detected yum (CentOS/RHEL). Installing Maven..."
        sudo yum install -y maven
    elif command -v dnf &> /dev/null; then
        echo "--> Detected dnf (Fedora). Installing Maven..."
        sudo dnf install -y maven
    elif command -v pacman &> /dev/null; then
        echo "--> Detected pacman (Arch Linux). Installing Maven..."
        sudo pacman -Syu --noconfirm maven
    elif command -v brew &> /dev/null; then
        echo "--> Detected Homebrew (macOS). Installing Maven..."
        brew install maven
    else
        echo "[!] Could not detect a supported package manager (apt-get, yum, dnf, pacman, brew)."
        echo "Please install Maven manually."
        exit 1
    fi

    # Verify installation
    if ! command -v mvn &> /dev/null; then
        echo "[!] Maven installation failed. Please install it manually."
        exit 1
    fi
    echo "[+] Maven has been installed."
fi

# Run the application
mvn exec:java -Dexec.mainClass="main.App"

if [ $? -ne 0 ]; then
    echo ""
    echo "[!] Error detected. Check database connection settings."
    read -p "Press [Enter] to exit..."
fi