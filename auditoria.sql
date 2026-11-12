-- Execute after Hibernate has created usuarios and reciclajes.
-- Re-running preserves existing audit records. Stop writes while installing triggers.
USE ambigps;

CREATE TABLE IF NOT EXISTS auditoria_sistema (
    id_auditoria BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_actor VARCHAR(150) NOT NULL,
    accion ENUM('INSERT', 'UPDATE', 'DELETE') NOT NULL,
    fecha_hora DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    tabla_afectada VARCHAR(100) NOT NULL,
    id_registro BIGINT NOT NULL,
    datos_anteriores JSON,
    datos_nuevos JSON
) ENGINE=InnoDB;

DROP TRIGGER IF EXISTS trg_auditoria_usuarios_insert;
DROP TRIGGER IF EXISTS trg_usuarios_insert;
CREATE TRIGGER trg_usuarios_insert
AFTER INSERT ON usuarios
FOR EACH ROW
    INSERT INTO auditoria_sistema (
        usuario_actor, accion, tabla_afectada, id_registro, datos_anteriores, datos_nuevos
    ) VALUES (
        COALESCE(@usuario_auditoria, USER()), 'INSERT', 'usuarios', NEW.id_usuario,
        NULL,
        JSON_OBJECT(
            'id_usuario', NEW.id_usuario,
            'id_rol', NEW.id_rol,
            'id_municipio', NEW.id_municipio,
            'nombre_completo', NEW.nombre_completo,
            'correo', NEW.correo,
            'telefono', NEW.telefono,
            'estado', NEW.estado
        )
    );

DROP TRIGGER IF EXISTS trg_auditoria_usuarios_update;
DROP TRIGGER IF EXISTS trg_usuarios_update;
CREATE TRIGGER trg_usuarios_update
AFTER UPDATE ON usuarios
FOR EACH ROW
    INSERT INTO auditoria_sistema (
        usuario_actor, accion, tabla_afectada, id_registro, datos_anteriores, datos_nuevos
    ) VALUES (
        COALESCE(@usuario_auditoria, USER()), 'UPDATE', 'usuarios', NEW.id_usuario,
        JSON_OBJECT(
            'id_usuario', OLD.id_usuario,
            'id_rol', OLD.id_rol,
            'id_municipio', OLD.id_municipio,
            'nombre_completo', OLD.nombre_completo,
            'correo', OLD.correo,
            'telefono', OLD.telefono,
            'estado', OLD.estado
        ),
        JSON_OBJECT(
            'id_usuario', NEW.id_usuario,
            'id_rol', NEW.id_rol,
            'id_municipio', NEW.id_municipio,
            'nombre_completo', NEW.nombre_completo,
            'correo', NEW.correo,
            'telefono', NEW.telefono,
            'estado', NEW.estado
        )
    );

DROP TRIGGER IF EXISTS trg_auditoria_usuarios_delete;
DROP TRIGGER IF EXISTS trg_usuarios_delete;
CREATE TRIGGER trg_usuarios_delete
AFTER DELETE ON usuarios
FOR EACH ROW
    INSERT INTO auditoria_sistema (
        usuario_actor, accion, tabla_afectada, id_registro, datos_anteriores, datos_nuevos
    ) VALUES (
        COALESCE(@usuario_auditoria, USER()), 'DELETE', 'usuarios', OLD.id_usuario,
        JSON_OBJECT(
            'id_usuario', OLD.id_usuario,
            'id_rol', OLD.id_rol,
            'id_municipio', OLD.id_municipio,
            'nombre_completo', OLD.nombre_completo,
            'correo', OLD.correo,
            'telefono', OLD.telefono,
            'estado', OLD.estado
        ),
        NULL
    );

DROP TRIGGER IF EXISTS trg_auditoria_reciclajes_insert;
DROP TRIGGER IF EXISTS trg_reciclajes_insert;
CREATE TRIGGER trg_reciclajes_insert
AFTER INSERT ON reciclajes
FOR EACH ROW
    INSERT INTO auditoria_sistema (
        usuario_actor, accion, tabla_afectada, id_registro, datos_anteriores, datos_nuevos
    ) VALUES (
        COALESCE(@usuario_auditoria, USER()), 'INSERT', 'reciclajes', NEW.id_reciclaje,
        NULL,
        JSON_OBJECT(
            'id_reciclaje', NEW.id_reciclaje,
            'id_solicitud', NEW.id_solicitud,
            'peso_kg', NEW.peso_kg,
            'descripcion', NEW.descripcion,
            'fecha_registro', NEW.fecha_registro
        )
    );

DROP TRIGGER IF EXISTS trg_auditoria_reciclajes_update;
DROP TRIGGER IF EXISTS trg_reciclajes_update;
CREATE TRIGGER trg_reciclajes_update
AFTER UPDATE ON reciclajes
FOR EACH ROW
    INSERT INTO auditoria_sistema (
        usuario_actor, accion, tabla_afectada, id_registro, datos_anteriores, datos_nuevos
    ) VALUES (
        COALESCE(@usuario_auditoria, USER()), 'UPDATE', 'reciclajes', NEW.id_reciclaje,
        JSON_OBJECT(
            'id_reciclaje', OLD.id_reciclaje,
            'id_solicitud', OLD.id_solicitud,
            'peso_kg', OLD.peso_kg,
            'descripcion', OLD.descripcion,
            'fecha_registro', OLD.fecha_registro
        ),
        JSON_OBJECT(
            'id_reciclaje', NEW.id_reciclaje,
            'id_solicitud', NEW.id_solicitud,
            'peso_kg', NEW.peso_kg,
            'descripcion', NEW.descripcion,
            'fecha_registro', NEW.fecha_registro
        )
    );

DROP TRIGGER IF EXISTS trg_auditoria_reciclajes_delete;
DROP TRIGGER IF EXISTS trg_reciclajes_delete;
CREATE TRIGGER trg_reciclajes_delete
AFTER DELETE ON reciclajes
FOR EACH ROW
    INSERT INTO auditoria_sistema (
        usuario_actor, accion, tabla_afectada, id_registro, datos_anteriores, datos_nuevos
    ) VALUES (
        COALESCE(@usuario_auditoria, USER()), 'DELETE', 'reciclajes', OLD.id_reciclaje,
        JSON_OBJECT(
            'id_reciclaje', OLD.id_reciclaje,
            'id_solicitud', OLD.id_solicitud,
            'peso_kg', OLD.peso_kg,
            'descripcion', OLD.descripcion,
            'fecha_registro', OLD.fecha_registro
        ),
        NULL
    );

