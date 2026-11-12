package com.example.seven.AmbiGPSBeta.service;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Supplier;

@Component
public class EscrituraAuditada {

    private final EntityManager entityManager;

    public EscrituraAuditada(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Transactional
    public <T> T ejecutar(Supplier<T> escritura) {
        var autenticacion = SecurityContextHolder.getContext().getAuthentication();
        String actor = autenticacion == null ? null : autenticacion.getName();
        establecerActor(actor);
        try {
            T resultado = escritura.get();
            entityManager.flush();
            return resultado;
        } finally {
            establecerActor(null);
        }
    }

    private void establecerActor(String actor) {
        entityManager.unwrap(Session.class).doWork(conexion -> {
            try (var sentencia = conexion.prepareStatement("SET @usuario_auditoria = ?")) {
                sentencia.setString(1, actor);
                sentencia.execute();
            }
        });
    }
}
