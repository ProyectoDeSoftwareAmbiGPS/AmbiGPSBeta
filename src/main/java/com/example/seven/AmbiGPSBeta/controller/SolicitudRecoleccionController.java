package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.model.SolicitudRecoleccion;
import com.example.seven.AmbiGPSBeta.model.EstadoSolicitud;
import com.example.seven.AmbiGPSBeta.service.SolicitudRecoleccionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudRecoleccionController {

    private final SolicitudRecoleccionService solicitudService;

    public SolicitudRecoleccionController(SolicitudRecoleccionService solicitudService) {
        this.solicitudService = solicitudService;
    }

    @PostMapping
    public ResponseEntity<SolicitudRecoleccion> crear(
            @Valid @RequestBody SolicitudRecoleccion solicitud) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(solicitudService.crearSolicitud(solicitud));
    }

    @GetMapping
    public ResponseEntity<List<SolicitudRecoleccion>> listar() {
        return ResponseEntity.ok(solicitudService.listarSolicitudes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SolicitudRecoleccion> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(solicitudService.buscarPorId(id));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<SolicitudRecoleccion>> buscarPorUsuario(
            @PathVariable Long idUsuario) {
        return ResponseEntity.ok(solicitudService.buscarPorUsuario(idUsuario));
    }

    @GetMapping("/reciclador/{idReciclador}")
    public ResponseEntity<List<SolicitudRecoleccion>> buscarPorReciclador(
            @PathVariable Long idReciclador) {
        return ResponseEntity.ok(solicitudService.buscarPorReciclador(idReciclador));
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<SolicitudRecoleccion>> buscarPorEstado(
            @PathVariable EstadoSolicitud estado) {
        return ResponseEntity.ok(solicitudService.buscarPorEstado(estado));
    }

    @GetMapping("/material/{idTipoMaterial}")
    public ResponseEntity<List<SolicitudRecoleccion>> buscarPorTipoMaterial(
            @PathVariable Integer idTipoMaterial) {
        return ResponseEntity.ok(solicitudService.buscarPorTipoMaterial(idTipoMaterial));
    }

    @PatchMapping("/{id}/asignar/{idReciclador}")
    public ResponseEntity<SolicitudRecoleccion> asignarReciclador(
            @PathVariable Long id,
            @PathVariable Long idReciclador) {
        return ResponseEntity.ok(
                solicitudService.asignarReciclador(id, idReciclador));
    }

    @PatchMapping("/{id}/estado/{estado}")
    public ResponseEntity<SolicitudRecoleccion> cambiarEstado(
            @PathVariable Long id,
            @PathVariable EstadoSolicitud estado) {
        return ResponseEntity.ok(
                solicitudService.cambiarEstado(id, estado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SolicitudRecoleccion> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody SolicitudRecoleccion solicitud) {
        return ResponseEntity.ok(
                solicitudService.actualizarSolicitud(id, solicitud));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        solicitudService.eliminarSolicitud(id);
        return ResponseEntity.noContent().build();
    }
}
