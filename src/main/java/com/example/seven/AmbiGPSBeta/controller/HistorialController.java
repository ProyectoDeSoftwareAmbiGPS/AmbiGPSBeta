package com.example.seven.AmbiGPSBeta.controller;

import com.example.seven.AmbiGPSBeta.model.Historial;
import com.example.seven.AmbiGPSBeta.service.HistorialService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/historial")
public class HistorialController {

    private final HistorialService historialService;

    public HistorialController(HistorialService historialService) {
        this.historialService = historialService;
    }

    @PostMapping
    public ResponseEntity<Historial> crear(
            @Valid @RequestBody Historial historial) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(historialService.crearHistorial(historial));
    }

    @GetMapping
    public ResponseEntity<List<Historial>> listar() {
        return ResponseEntity.ok(historialService.listarHistorial());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Historial> buscar(@PathVariable Long id) {
        return ResponseEntity.ok(historialService.buscarPorId(id));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<Historial>> buscarPorUsuario(
            @PathVariable Long idUsuario) {
        return ResponseEntity.ok(historialService.buscarPorUsuario(idUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Historial> actualizar(
            @PathVariable Long id,
            @Valid @RequestBody Historial historial) {
        return ResponseEntity.ok(
                historialService.actualizarHistorial(id, historial));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        historialService.eliminarHistorial(id);
        return ResponseEntity.noContent().build();
    }
}
