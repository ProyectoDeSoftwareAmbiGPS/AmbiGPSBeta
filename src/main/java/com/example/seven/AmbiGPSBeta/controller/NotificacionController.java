package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.model.Notificacion;
import com.example.seven.AmbiGPSBeta.service.NotificacionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notificaciones")
public class NotificacionController {

    private final NotificacionService notificacionService;

    public NotificacionController(NotificacionService notificacionService) {
        this.notificacionService = notificacionService;
    }

    @PostMapping
    public ResponseEntity<Notificacion> crear(
            @Valid @RequestBody Notificacion notificacion) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(notificacionService.crearNotificacion(notificacion));
    }

    @GetMapping
    public ResponseEntity<List<Notificacion>> listar() {
        return ResponseEntity.ok(notificacionService.listarNotificaciones());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notificacion> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.buscarPorId(id));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Notificacion>> buscarPorUsuario(
            @PathVariable Long idUsuario) {
        return ResponseEntity.ok(notificacionService.buscarPorUsuario(idUsuario));
    }

    @GetMapping("/usuario/{idUsuario}/no-leidas")
    public ResponseEntity<List<Notificacion>> buscarNoLeidas(
            @PathVariable Long idUsuario) {
        return ResponseEntity.ok(
                notificacionService.buscarPorUsuarioNoLeidas(idUsuario));
    }

    @GetMapping("/solicitud/{idSolicitud}")
    public ResponseEntity<List<Notificacion>> buscarPorSolicitud(
            @PathVariable Long idSolicitud) {
        return ResponseEntity.ok(notificacionService.buscarPorSolicitud(idSolicitud));
    }

    @PatchMapping("/{id}/marcar-leida")
    public ResponseEntity<Notificacion> marcarComoLeida(@PathVariable Long id) {
        return ResponseEntity.ok(notificacionService.marcarComoLeida(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notificacion> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Notificacion notificacion) {
        return ResponseEntity.ok(
                notificacionService.actualizarNotificacion(id, notificacion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        notificacionService.eliminarNotificacion(id);
        return ResponseEntity.noContent().build();
    }
}
