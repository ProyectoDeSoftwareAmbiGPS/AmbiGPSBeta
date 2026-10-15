# 🚀 INICIO RÁPIDO - AmbiGPSBeta (BD: ambigps)

## ✅ Cambios Aplicados

| Cambio | Anterior | Nuevo |
|--------|----------|-------|
| **Base de Datos** | sistema_reciclaje | **ambigps** |
| **ddl-auto** | none | **update** |
| **Compilación** | ✅ BUILD SUCCESS | ✅ BUILD SUCCESS |

---

## 🔧 Opción A: Setup Automático (Recomendado)

### Windows PowerShell

```bash
# 1. Colocarse en carpeta del proyecto
cd "d:\Mio\Docuemnto\PROYECTO DE SOFTWARE II\AmbiGPSBeta"

# 2. Crear base de datos
mysql -u root -p < create_database.sql
# Ingresar contraseña MySQL (por defecto: vacía o "root")

# 3. Compilar
.\mvnw.cmd clean compile -DskipTests
# Resultado esperado: BUILD SUCCESS

# 4. Ejecutar aplicación
.\mvnw.cmd spring-boot:run
# Resultado esperado: Tomcat started on port(s): 8080 (http)
```

### Verificar que funciona

En otra terminal:
```bash
curl http://localhost:8080/api/roles
# Debe retornar: [{"idRol": 1, "nombre": "ADMIN", ...}]
```

---

## 🔧 Opción B: Setup Manual (Si no tienes MySQL en PATH)

### Crear Base de Datos

1. **Abrir MySQL Workbench** o cliente MySQL
2. **Copiar y ejecutar** el contenido de `create_database.sql`:

```sql
CREATE DATABASE IF NOT EXISTS ambigps 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE ambigps;
```

3. **Verificar**:
```sql
SHOW DATABASES;
-- Debe aparecer "ambigps" en la lista
```

### Ejecutar Aplicación

```bash
cd "d:\Mio\Docuemnto\PROYECTO DE SOFTWARE II\AmbiGPSBeta"
.\mvnw.cmd spring-boot:run
```

**Esperar a ver:** `Tomcat started on port(s): 8080`

---

## ✅ Qué Sucede Automáticamente

Cuando la aplicación inicia con `ddl-auto=update`:

1. ✅ **Crea automáticamente todas las tablas:**
   - usuarios
   - roles
   - departamentos
   - municipios
   - solicitudes_recoleccion
   - reciclajes
   - qr
   - notificaciones
   - tipos_material
   - historial

2. ✅ **Inserta datos iniciales:**
   - 3 roles (ADMIN, RECICLADOR, USUARIO)
   - 4 tipos de material (Plástico, Papel, Vidrio, Metal)
   - 4 departamentos
   - 8 municipios
   - 5 usuarios (1 admin, 2 recicladores, 2 usuarios normales)
   - Todas las contraseñas **hasheadas en BCrypt**

3. ✅ **Muestra credenciales en consola:**
   ```
   ========== DATOS DE PRUEBA CARGADOS ==========
   ✅ admin@ambigps.com / Admin@123456
   ✅ reciclador1@ambigps.com / Recicl@123456
   ...
   ```

---

## 👥 Usuarios de Prueba (Auto-cargados)

| Email | Contraseña | Rol | Estado |
|-------|-----------|-----|--------|
| admin@ambigps.com | Admin@123456 | ADMIN | ACTIVO |
| reciclador1@ambigps.com | Recicl@123456 | RECICLADOR | ACTIVO |
| reciclador2@ambigps.com | Recicl@123456 | RECICLADOR | ACTIVO |
| usuario1@ambigps.com | Usuar@123456 | USUARIO | ACTIVO |
| usuario2@ambigps.com | Usuar@123456 | USUARIO | ACTIVO |

---

## 🧪 Probar Endpoints

### 1. Sin Postman (con curl)

```bash
# Ver todos los roles
curl http://localhost:8080/api/roles

# Ver todos los usuarios
curl http://localhost:8080/api/usuarios

# Ver todas las solicitudes
curl http://localhost:8080/api/solicitudes
```

### 2. Con Postman (Recomendado)

1. Importar: `AmbiGPSBeta-Postman-Collection.json`
2. Ejecutar flujo completo (10-15 min)
3. Ver resultados en la consola del servidor

---

## 🔍 Verificar Base de Datos

```bash
mysql -u root -p ambigps

# Ver tablas
SHOW TABLES;

# Ver usuarios
SELECT id_usuario, nombre_completo, correo FROM usuarios;

# Ver roles
SELECT * FROM roles;
```

---

## ⚠️ Solución de Problemas

### Error: "Access denied for user 'root'@'localhost'"

**Causa:** Contraseña MySQL incorrecta

**Solución:**
```bash
mysql -u root -p
# Ingresar contraseña correcta
```

### Error: "Can't connect to MySQL server"

**Causa:** MySQL no está ejecutándose

**Solución:**
```bash
# Verificar que MySQL corre
mysql -u root -p -e "SELECT 1;"

# Si falla, iniciar MySQL:
# Windows (XAMPP): Abrir XAMPP Control Panel, click Start en MySQL
# Mac: brew services start mysql-community-server
# Linux: sudo systemctl start mysql
```

### Base de datos vacía (sin datos)

**Causa:** DataLoaderConfig no ejecutó

**Solución:**
1. Revisar logs de la aplicación (debe mostrar "DATOS DE PRUEBA CARGADOS")
2. Verificar que `DataLoaderConfig.java` existe
3. Reiniciar la aplicación

### Error: "Table doesn't exist"

**Causa:** Las tablas no se crearon automáticamente

**Solución:**
- Verificar que `ddl-auto=update` en `application.properties`
- Ejecutar `create_database.sql` manualmente
- Reiniciar la aplicación

---

## 📋 Checklist de Verificación

- [ ] MySQL corriendo: `mysql -u root -p -e "SHOW DATABASES;"`
- [ ] BD creada: Debe aparecer "ambigps"
- [ ] Proyecto compilado: `BUILD SUCCESS`
- [ ] Aplicación inicia: `Tomcat started on port(s): 8080`
- [ ] Endpoint funciona: `curl http://localhost:8080/api/roles` → 200 OK
- [ ] Datos cargados: Ver usuarios en consola de inicio
- [ ] Postman importado: AmbiGPSBeta-Postman-Collection.json

---

## 📞 Configuración Actual

**Archivo:** `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/ambigps?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
```

**Si necesitas cambiar usuario/contraseña MySQL:**
1. Editar `application.properties`
2. Cambiar `spring.datasource.username` y `spring.datasource.password`
3. Compilar: `.\mvnw.cmd clean compile`
4. Ejecutar: `.\mvnw.cmd spring-boot:run`

---

## 🚀 Próximo Paso

Ejecuta en terminal:

```bash
.\mvnw.cmd spring-boot:run
```

Espera a ver:
```
Tomcat started on port(s): 8080 (http)
```

¡Luego prueba los endpoints en Postman! 🎉

---

**Última actualización:** 2026-10-07  
**Base de datos:** ambigps ✅  
**Compilación:** BUILD SUCCESS ✅  
**Estado:** Listo para usar 🚀
