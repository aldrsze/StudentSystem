@echo off
title Student Management System Launcher (Maven)
color 0A

echo ==================================================
echo      Starting Student Management System (Maven)
echo ==================================================

:: 1. Check if pom.xml exists in the current directory
if not exist "pom.xml" (
    echo [!] Error: pom.xml not found. 
    echo Please run this .bat file from your project's root folder.
    pause
    exit
)

:: 2. Execute the application using Maven
:: This calls the main entry point in your project
call mvn exec:java -Dexec.mainClass="main.App"

:: 3. Error Handling
if %ERRORLEVEL% NEQ 0 (
    echo.
    echo [!] Application exited with an error.
    echo.
    echo Possible issues:
    echo - MySQL service is not running.
    echo - Database credentials in DatabaseConnection.java are incorrect.
    echo - Maven dependencies are not yet downloaded (run 'mvn install' first).
    echo.
    pause
)

exit