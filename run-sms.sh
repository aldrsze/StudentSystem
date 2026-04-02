#!/bin/bash

echo "=================================================="
echo "     Student Management System Launcher (Maven)   "
echo "=================================================="

# Check for Maven
if ! command -v mvn &> /dev/null
then
    echo "[!] Maven is not installed. Please install it to continue."
    exit
fi

# Run the application
mvn exec:java -Dexec.mainClass="main.App"

if [ $? -ne 0 ]; then
    echo ""
    echo "[!] Error detected. Check database connection settings."
    read -p "Press [Enter] to exit..."
fi