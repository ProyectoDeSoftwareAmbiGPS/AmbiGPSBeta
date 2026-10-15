@echo off
REM ============================================================
REM Script para setup automático de AmbiGPSBeta
REM ============================================================
REM Requisitos: MySQL debe estar ejecutándose en localhost:3306
REM Contraseña root puede ser diferente
REM ============================================================

echo.
echo ========== SETUP AmbiGPSBeta ==========
echo.

REM Crear base de datos
echo Paso 1: Creando base de datos 'ambigps'...
mysql -u root -p < create_database.sql

if %errorlevel% neq 0 (
    echo.
    echo [ERROR] No se pudo conectar a MySQL.
    echo Verifique que:
    echo - MySQL está ejecutándose en localhost:3306
    echo - Usuario: root
    echo - La contraseña es correcta
    echo.
    pause
    exit /b 1
)

echo.
echo Paso 2: Compilando proyecto...
call .\mvnw.cmd clean compile -DskipTests

if %errorlevel% neq 0 (
    echo.
    echo [ERROR] Compilación fallida
    pause
    exit /b 1
)

echo.
echo ========== SETUP COMPLETADO ==========
echo.
echo Siguiente paso: Ejecutar la aplicación
echo.
echo Comando: mvnw spring-boot:run
echo.
echo Credenciales de prueba:
echo - Email: admin@ambigps.com
echo - Contraseña: Admin@123456
echo.
pause
