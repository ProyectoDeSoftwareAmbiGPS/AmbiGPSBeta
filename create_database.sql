-- ============================================================
-- Script para crear base de datos AmbiGPSBeta
-- ============================================================
-- Ejecutar con: mysql -u root -p < create_database.sql
-- O copiar y pegar en MySQL Workbench
-- ============================================================

-- Crear base de datos si no existe
CREATE DATABASE IF NOT EXISTS ambigps 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

-- Usar la base de datos
USE ambigps;

-- Mostrar confirmación
SELECT CONCAT('✓ Base de datos AMBIGPS creada exitosamente') AS Resultado;
SELECT CONCAT('Conexión: jdbc:mysql://localhost:3306/ambigps') AS URL;
SELECT CONCAT('Usuario: root') AS Usuario;
