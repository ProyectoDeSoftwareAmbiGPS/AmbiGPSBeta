# 🌍 AmbiGPSBeta - Sistema de Gestión de Recolección de Reciclaje

**Versión:** 1.0.0  
**Base de Datos:** ambigps ✅  
**Estado:** ✅ Production Ready  
**Compilación:** ✅ BUILD SUCCESS

---

## 🎯 Inicio Rápido (2 minutos)

### 1. Crear Base de Datos
```bash
mysql -u root -p < create_database.sql
# O ejecutar manualmente en MySQL:
# CREATE DATABASE ambigps CHARACTER SET utf8mb4;
```

### 2. Ejecutar Aplicación
```bash
.\mvnw.cmd spring-boot:run
```

**Resultado esperado:**
```
Tomcat started on port(s): 8080 (http)
-------- DATOS DE PRUEBA CARGADOS --------
✅ admin@ambigps.com / Admin@123456
```

### 3. Verificar
```bash
curl http://localhost:8080/api/roles
# Debe retornar JSON con 3 roles
```

---

## 📚 Documentación

| Archivo | Propósito |
|---------|----------|
| **[INICIO_RAPIDO.md](./INICIO_RAPIDO.md)** | 🚀 Setup paso a paso |
| **consultas_sql.sql** | 🗄️ 15 queries SQL |

---

## 🔧 Configuración

**Archivo:** `src/main/resources/application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ambigps
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

### ¿Qué es "ddl-auto=update"?
- ✅ Crea tablas automáticamente
- ✅ Inserta datos iniciales (DataLoaderConfig)
- ✅ No borra datos en startups siguientes

---

## 👥 Usuarios Predefinidos

Se cargan automáticamente al iniciar:

```
ADMIN
├─ admin@ambigps.com / Admin@123456

RECICLADORES
├─ reciclador1@ambigps.com / Recicl@123456
└─ reciclador2@ambigps.com / Recicl@123456

USUARIOS
├─ usuario1@ambigps.com / Usuar@123456
└─ usuario2@ambigps.com / Usuar@123456
```

Todas las contraseñas están **encriptadas en BCrypt**.

---

## 🧪 Probar Endpoints

### Con curl
```bash
# Usuarios
curl http://localhost:8080/api/usuarios

# Roles
curl http://localhost:8080/api/roles

# Solicitudes
curl http://localhost:8080/api/solicitudes
```

### Con Postman
1. Importar: `AmbiGPSBeta-Postman-Collection.json`
2. Ejecutar endpoints

---

## 📦 Estructura

```
AmbiGPSBeta/
├── src/main/java/com/example/seven/AmbiGPSBeta/
│   ├── model/           (10 entidades JPA)
│   ├── repository/      (10 repositorios)
│   ├── service/         (11 servicios)
│   ├── controller/      (11 controladores)
│   ├── config/          (Seguridad + Init datos)
│   └── AmbiGpsBetaApplication.java
├── create_database.sql  (BD setup automático)
├── setup.bat           (Setup Windows batch)
└── INICIO_RAPIDO.md    (Instrucciones detalladas)
```

---

## 🔐 Seguridad

✅ Passwords en BCrypt  
✅ Validaciones en modelos  
✅ Verificación de rol en servicios  
✅ Spring Security configurado

---

## 📊 API Endpoints

**45+ endpoints** divididos en:
- Usuarios (7 endpoints)
- Solicitudes de Recolección (9 endpoints)
- Roles (4 endpoints)
- Tipos de Material (4 endpoints)
- Departamentos, Municipios, Reciclajes, QR, Notificaciones, Historial

---

## ⚠️ Troubleshooting

### Error: "Access denied for user 'root'"
Cambiar contraseña en `application.properties`:
```properties
spring.datasource.password=tu_contraseña_mysql
```

### Error: "Can't connect to MySQL server"
Verificar que MySQL está ejecutándose:
```bash
mysql -u root -p -e "SELECT 1;"
```

### Base de datos vacía
Reiniciar la aplicación para que cargue los datos.

---

## 🚀 Próximos Pasos

1. Lee: [INICIO_RAPIDO.md](./INICIO_RAPIDO.md)
2. Ejecuta: `.\mvnw.cmd spring-boot:run`
3. Prueba: `curl http://localhost:8080/api/roles`
4. Importa colección Postman (si tienes)

---

**Estado:** ✅ Listo para usar  
**Base de datos:** ambigps  
**Compilación:** BUILD SUCCESS
